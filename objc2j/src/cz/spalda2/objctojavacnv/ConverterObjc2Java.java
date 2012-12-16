package cz.spalda2.objctojavacnv;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.CommonToken;

import cz.spalda2.objctojavacnv.antlr.output.ObjcLexer;
import cz.spalda2.objctojavacnv.antlr.output.ObjcParser;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * User: spalda2
 * Date: 12.06.12
 * Time: 1:14
 */

// package cz.spalda2.objctojavacnv.antlr.output;

public class ConverterObjc2Java {

	int iBlockCount = 0;
	boolean iIgnorePlainNodesBrackets = false;
	boolean iIgnorePlainNodesCurrledBrackets = false;
	boolean iIgnorePlainNodesNewLineOnSemicolon = false;
//	boolean iNoNewLines = false;
	
	String iGlobalScope = null;
	//TODO the way we work with the below 2 vars assumes no nested classes and/or methods which is fine for objc
	String iCurrentClassName = null;
	String iCurrentMethodName = null;
	int iCurrentMethodMark = 0; //position of currently processed method implementation in iJavaCode
	int iCurrentMethodBlockCount = 0;
	
	StringBuffer iJavaCode = new StringBuffer();
	StringBuffer iJavaCodeGlobal = new StringBuffer();
	
    //    private static final List<String> RESTRICTED_METHODS = Arrays.asList("release", "retain", /*"autorelease", */"dealloc"/*, "init"*/);
    private static final Map<String, String> methodTranslation;
    static {
    	methodTranslation = new HashMap<String, String>();
    	methodTranslation.put("alloc", "new");
    	methodTranslation.put("retain", "");
    	methodTranslation.put("release", " = null");
    	methodTranslation.put("autorelease", "");
    	methodTranslation.put("isKindOfClass", "<<isInstance"); //<< => swap value and parameter
    	methodTranslation.put("class()", "class");
    	//dictionary->map methods
    	methodTranslation.put("objectForKey", "get");
    	//object->object methods
    	methodTranslation.put("isEqual", "equals");
    	//NSString -> String methods
    	methodTranslation.put("isEqualToString", "equals");
    	methodTranslation.put("NSAssert","Assert.assertTrue");
    };

    private static final Map<String, String> keywordTranslation;
    static {
    	keywordTranslation = new HashMap<String, String>();
    	keywordTranslation.put("nil", "null");
    	keywordTranslation.put("NULL", "null");
    	keywordTranslation.put("YES", "true");
    	keywordTranslation.put("NO", "false");
    	keywordTranslation.put("@selector", "this.getClass().getMethod"); //this need manual completion as it needs a look at the method params to complete method signature
    	keywordTranslation.put("id", "Object"); //for lack of anything else
    	keywordTranslation.put("NSObject", "Object"); //TODO convert stuff like (void\\s\*) to Object
    	keywordTranslation.put("NSDictionary", "Map<Object,Object>"); //this needs manual when it comes to calls like NSDictionary.class
    	keywordTranslation.put("NSMutableDictionary", "Map<Object,Object>");
    	keywordTranslation.put("NSString", "String");
    	keywordTranslation.put("NSNumber", "Number");
    	keywordTranslation.put("NSInteger", "int");
    	keywordTranslation.put("NSUInteger", "unsigned int");
    	keywordTranslation.put("NSArray", "Vector<Object>");
    	keywordTranslation.put("NSMutableArray", "Vector<Object>");
    	keywordTranslation.put("BOOL", "boolean");
    	keywordTranslation.put("self", "this");
    	keywordTranslation.put("->", ".");
    	keywordTranslation.put("extern", "public");
    	keywordTranslation.put("@try", "try");
    	keywordTranslation.put("@throw", "throw");
    	keywordTranslation.put("@catch", "catch");
    	keywordTranslation.put("@finally", "finally");
    	keywordTranslation.put("@private", "private");
    	keywordTranslation.put("@public", "public");
    	keywordTranslation.put("@protocol", "public interface");
    	keywordTranslation.put("@interface", "public class");
    	keywordTranslation.put("@implementation", "");
    	keywordTranslation.put("@end", "}");
    	keywordTranslation.put("@synthetize", "");
    	keywordTranslation.put("@synchronized", "synchronized");
    	keywordTranslation.put("@class", "");
    	keywordTranslation.put("\"C\"", "");
    };

    void newLines(int count,StringBuffer ret) {
		while (count-- > 0) {
			ret.append('\n');
		}
		int b = iBlockCount;
		while (b-- > 0) {
			ret.append('\t');
		}
    }
    
    boolean isNodeWithChildern(Object node) {
    	return CommonTree.class.isInstance(node) && ((CommonTree)node).getChildren() != null;
    }
    
    void parseFallthroughNode(Object node, StringBuffer ret) {
    	//it could be xxx_COMMENT and such not explicitly caught anywhere
    	if (CommonTree.class.isInstance(node)) {
    		CommonTree tr = (CommonTree)node;
        	if (tr.token.getType() == ObjcParser.IF0_COMMENT) {
        		ret.append("\n/*").append(((CommonTree)node).getChild(0).toString()).append("\n*/");
        		return;
        	} else if (tr.token.getType() == ObjcParser.SINGLE_COMMENT) {
        		ret.append(tr.getChild(0).toString()); //this will append \n as well as a part of da comment
        		newLines(0,ret); //add desired number of \t
        		return;
        	}
    	}
    	if (node != null) {
    		if (isNodeWithChildern(node)) {
        		CommonTree tree = (CommonTree)node;
    	        for (Object child : tree.getChildren()) {
    	        	if (isNodeWithChildern(child)) {
    		        	CommonTree tr = (CommonTree) child;
    		            parseFallthroughNode(tr,ret);
    		        } else {
	                	//ret.append(' ');
    		        	processPlainNode(child,ret);
    		        }
    	        }
    		} else {
            	//ret.append(' ');
    			processPlainNode(node,ret);
    		}
    	}
    }

    void processFragment(Object node, String str, StringBuffer ret) {
		String translated = keywordTranslation.get(str);
		if (translated != null) {
			str = translated;
		}
    	if (iIgnorePlainNodesBrackets && str.matches("[()]")) {
    		return;
    	}
    	if (iIgnorePlainNodesCurrledBrackets && str.matches("[{}]")) {
    		return;
    	}
    	if (str.length() > 1 || str.matches("[^&:*]")) { //cannot ignore '*' cose it could be an operator
    		if (str.startsWith("@\"")) { //NSString
    			//replace obj c formating %@ by %s ?
    			str = str.replaceAll("%@", "%s");
        		ret.append(str.substring(1));
    		} else if (str.equals("}")) {
    			//we take it as an end of a block
    			iBlockCount--;
    			String s = ret.substring(ret.length() - 1);
    			if (s.equals("\n")) {
    				//insert the right number of tabs
    				newLines(0,ret);
    			} else if (s.equals("\t")){
    				//remove one tab
    				ret.deleteCharAt(ret.length() - 1);
    			} else {
    				newLines(1,ret);    				
    			}
    			ret.append(str);
    			iBlockCount++; //let it be managed explicitly
    		} else {
    			ret.append(str);
    			int n = 1;
	    		if (!iIgnorePlainNodesNewLineOnSemicolon && str.equals(";")) {
	    			//kinda experimental formating
	    			if (CommonTree.class.isInstance(node)) {
	    				CommonTree tr = (CommonTree)((CommonTree)node).getParent();
	    				int type;
	    				while (tr != null && (type = tr.token.getType()) != ObjcLexer.STRUCT && type != ObjcLexer.ENUM && type != ObjcLexer.INTERFACE) {
	    					node = tr;
	    					tr = (CommonTree)tr.getParent();
	    				}
	    				if (tr != null) {
		    				int idx = ((CommonTree)node).getChildIndex();
		    				if (tr.getChildCount() > ++idx && ((CommonTree)tr.getChild(idx)).token.getType() == ObjcLexer.SINGLE_COMMENT) {
		    					//let the commet be on the same line
		    					n = 0;
		    				}
	    				}
	    			}
	    			if (n > 0) {
	    				newLines(1,ret);
	    			}
	    		}
    		}
    	}	
    }
    
    void processPlainNode(Object node, StringBuffer ret) {
    	String str = node.toString();
    	processFragment(node, str, ret);
    }
    
    void parseTypePlain(CommonTree tree, StringBuffer ret) {
    	
    	iIgnorePlainNodesBrackets = true;
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        } else {
	        	processPlainNode(child,ret);
            }
        }
    	iIgnorePlainNodesBrackets = false;
    }

    void parseTypeCast(CommonTree tree, StringBuffer ret) {
    	//here we need to somehow find ifit's a cast of an object which's method is being called
    	CommonTree val = (CommonTree)tree.getParent();
    	if (tree.getChildIndex() == 0 && val.token.getType() == ObjcParser.VALUE && val.getChildIndex() == 0) {
    		//it's the 1st type-cast of the 1st token....
    		CommonTree msg = (CommonTree)val.getParent();
    		if (msg.token.getType() == ObjcParser.METHOD_MSG) {
    			//...of a message call => we wrap the whole value in brackets
    			ret.append('(');
    			CommonTree node = new CommonTree(new CommonToken(ObjcLexer.LETTER, ")"));
    			val.addChild(node);
    		}
    	}
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
            	parseFallthroughNode((CommonTree)child,ret);
	        } else {
	        	processPlainNode(child,ret);
            }
        }
    }

    void parseValueInternal(CommonTree tree, StringBuffer ret) {
    	//TODO watch for things like self = something and discard them
    	//need to do conventional loop since we may end up adding nodes here and there
        for (int idx = 0; idx < tree.getChildCount(); idx++) {
        	Object child = tree.getChild(idx);
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	            case ObjcParser.METHOD_MSG:
	            	translateMethodMsg(tr,ret);
	            	break;
	            case ObjcParser.ARRAY_VALUE:
	            	CommonTree trName = (CommonTree)tr.getFirstChildWithType(ObjcParser.NAME);
	            	String name = trName.getChild(0).toString();
	            	ret.append(name);
	                for (Object chld : tr.getChildren()) {
	                	if (isNodeWithChildern(child)) {
	        	        	CommonTree trValue = (CommonTree) chld;
	        	        	if (trValue.token.getType() == ObjcParser.VALUE) {
	        	        		String value = parseValue(trValue);
	        	        		ret.append("[").append(value).append("]");
	        	        	}
	                	}
	                }
	            	break;
	            case ObjcParser.QUESTION_MARK_IF:
	        		ret.append(" ? ");
	        	    CommonTree trValue = (CommonTree)tr.getChild(0);
	        		parseValueInternal(trValue,ret);
	        		ret.append(" : ");
	        	    trValue = (CommonTree)tr.getChild(1);
	        		parseValueInternal(trValue,ret);
	            	break;
	            case ObjcParser.TYPE_CAST:
	            	parseTypeCast(tr,ret);
	            	break;
	            case ObjcParser.EXPR_NOT:
	            	ret.append('!');
	            	parseValueInternal(tr,ret);
	            	break;
	            case ObjcParser.EXPR_NEGATE:
	            	ret.append('-');
	            	parseValueInternal(tr,ret);
	            	break;
	            case ObjcParser.EXPR_COMPLEMENT:
	            	ret.append('~');
	            	parseValueInternal(tr,ret);
	            	break;
	            case ObjcParser.VALUE:
	            	parseValueInternal(tr,ret);
	            	break;
	            case ObjcParser.BLOCK_CALL:
	            	parseBlockCall(tr,ret);
	            	break;
	            case ObjcParser.PARAMS:
	            	String params = parseMethodParams(tr);
	            	ret.append('(').append(params).append(')');
	            	break;
	            case ObjcParser.OP:
	            	ret.append(' ').append(tr.getChild(0).toString()).append(' ');
	            	break;
	            case ObjcParser.NAME:
	            case ObjcParser.STRING:
	            case ObjcParser.BOOL:
	            case ObjcParser.NUMBER:
	            case ObjcParser.ACCESS:
                	processPlainNode(tr.getChild(0),ret);
                	break;
                default:
                	parseFallthroughNode(tr,ret);
                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }
}
    
    String parseValue(CommonTree tree) {
    	if (tree.token.getType() != ObjcLexer.VALUE) {
    		return "";
    	}
    	StringBuffer ret = new StringBuffer();
    	parseValueInternal(tree,ret);
        return ret.length() > 0 ? ret.toString() : "";
    }
    
    void parseMethodParam(CommonTree tree, StringBuffer ret) {
    	
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.TYPE_PLAIN:
	                	parseTypePlain(tr,ret);
	                	break;
	                case ObjcParser.VALUE:
	                	ret.append(' ');
	                	parseValueInternal(tr,ret);
	                	break;
	                case ObjcParser.PARAM_NAME:
	                	String name = tr.getChild(0).toString();
	                	ret.append(' ');
	                	ret.append(name);
	                	break;
	                case ObjcParser.PARAM_PREFIX:
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	iIgnorePlainNodesBrackets = true;
	        	processPlainNode(child,ret);
	        	iIgnorePlainNodesBrackets = false;
            }
        }
    }
    
    String parseMethodParams(CommonTree tree) {
    	int type = tree.token.getType();
    	if (type != ObjcLexer.METHOD_PARAMS && type != ObjcLexer.PARAMS) {
    		return null;
    	}
    	StringBuffer ret = new StringBuffer();
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.METHOD_PARAM:
	                	if (ret.length() > 0) {
	                		ret.append(", ");
	                	}
	                	parseMethodParam(tr,ret);
	                	break;
	                default:
	                	//parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	//processPlainNode(child,ret);
	        }
        }
        return ret.length() > 0 ? ret.toString() : "";
    }
    
    void translateMethodMsg(CommonTree tree, StringBuffer ret) {
    	//checks for constructs like:
    	//[[name alloc] init*:*]
    	//[name retain]
    	//[name release]
    	//[name autorelease]
    	if (tree.token.getType() != ObjcLexer.METHOD_MSG) {
    		return;
    	}
    	String className = null;
    	String value = null;
    	String translatedName = null;
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.VALUE:
	                	if (tr.getChildCount() == 1 && isNodeWithChildern(tr.getChild(0))) {
		                	//special check for alloc
	                		CommonTree ctr = (CommonTree)tr.getChild(0);
	                		if (ctr.token.getType() == ObjcParser.METHOD_MSG) {
	                			String name = ctr.getFirstChildWithType(ObjcParser.METHOD_NAME).getChild(0).toString();
	                			if (name.equals("alloc")) {
	                				className = parseValue((CommonTree)ctr.getFirstChildWithType(ObjcParser.VALUE));
	                				//and remove the ctr METHOD_MSG node
	                				tr.deleteChild(0);
	                			}
	                		}
	                	}
	                	if (className == null) {
	                		value = parseValue(tr);
	                		//don't append here => we might want to omit the call altogether
	                	}
	                    break;
	                case ObjcParser.METHOD_NAME:
	                	if (className != null) {
	                		//we found alloc => translate to new
	                		ret.append("new ").append(className);
	                	} else {
	                		String name = ((CommonTree) child).getChild(0).toString();
	                		if (value.equals("super") && iCurrentClassName.equals(iCurrentMethodName) && name.startsWith("init")) {
		                		//here we ignore METHOD_NAME since we assume it's a call to super class constructor
		                		ret.append(value);
	    	            		if (tree.getFirstChildWithType(ObjcParser.METHOD_PARAMS) == null) {
	    	            			//no params
	    	                    	ret.append("()");
	    	            		}
		                	} else {
		                		translatedName = keywordTranslation.get(name);
		                		if (translatedName == null) {
		                			translatedName = name;
		                		}
		                		String n = translatedName;
		                		name = methodTranslation.get(translatedName);
		                		if (name != null) {
		                			translatedName = name;
		                		}
		                		if (!translatedName.startsWith("<<")) { //"<<" => need swap value for parameter
		                			//empty => omit the method name, == '-' omit the call alltogether
		                			if (translatedName.length() == 0) {
		                				//if we are not part of VARIABLE_ASSIGNMENT or VALUE we comment out the call
		                				if (tr.getAncestor(ObjcParser.VARIABLE_ASSIGNMENT) == null && tr.getAncestor(ObjcParser.VALUE) == null) {
		                					ret.append("//");
		                					translatedName = n;
		                				}
		                			}
		                			if (!translatedName.equals("-")) {
		                				ret.append(value);
		                				//if empty translatedName => method call translates to value only
		                				if (translatedName.length() > 0) {
		                					if (translatedName.startsWith(" =")) {
			                					ret.append(translatedName);		                						
		                					} else {
			                					ret.append('.');
			                					name = translatedName;
					    	            		if (tree.getFirstChildWithType(ObjcParser.METHOD_PARAMS) == null) {
					    	            			//no params
					    	            			name += "()";
					    	            			n = methodTranslation.get(name);
					    	            			ret.append(n != null ? n : name);
					    	            		} else {
					    	            			ret.append(translatedName);
					    	            		}
		                					}
		                				}
		                				if (translatedName.startsWith("init")) {
		                					//cannot match it against class => comment
		                					ret.append("/*TODO CONV possible constructor*/");
		                				}
			                		}
		                			translatedName = null;
		                		}
		                	}
	                	}
	                    break;
	                case ObjcParser.METHOD_PARAMS:
	                	String params = parseMethodParams(tr);
	                	if (translatedName != null) { //=> need to swap value for params
	                		ret.append(params).append('.').append(translatedName.substring(2)); //skip "<<" at the start of the string
	                		params = value;
	                	}
                		ret.append('(').append(params).append(')');
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }
    }
    
    String translateClassicalMethod(CommonTree tree) {
    	StringBuffer ret = new StringBuffer();
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.NAME:
	                	String name = tr.getChild(0).toString();
	                	String translated = methodTranslation.get(name);
	                	ret.append(translated != null ? translated : name);
	                	break;
		            case ObjcParser.PARAMS:
		            	String params = parseMethodParams(tr);
		            	ret.append(params);
		            	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }
        return ret.length() > 0 ? ret.toString() : "";
    }
    
    void parseBlockCall(CommonTree tree, StringBuffer ret) {
    	//here we need to create an extra interface
    	int blockCount = iBlockCount;
    	iBlockCount = iCurrentMethodBlockCount;
    	String interfaceName = String.valueOf(iJavaCode.length());
    	String fragment = iJavaCode.substring(iCurrentMethodMark);
    	iJavaCode.delete(iCurrentMethodMark, iJavaCode.length());
    	newLines(2,iJavaCode);
    	iJavaCode.append("private static interface Block").append(interfaceName).append(" {");
    	iBlockCount++;
    	newLines(2,iJavaCode);
    	iJavaCode.append("void callback");
    	String params = null;
    	CommonTree tr = (CommonTree)tree.getFirstChildWithType(ObjcParser.PARAMS);
    	if (tr == null) {
    		iJavaCode.append("();");
    	} else {
        	params = parseMethodParams(tr);
        	iJavaCode.append('(').append(params).append(");");
    	}
    	iBlockCount--;
    	newLines(1,iJavaCode);
    	iJavaCode.append('}');
    	newLines(1,iJavaCode);
        iCurrentMethodMark = iJavaCode.length();
    	iJavaCode.append(fragment);

        //and then create an anonymous object in place of the block
        iBlockCount = blockCount + 2;
        ret.append("new Block").append(interfaceName).append("() {");
        newLines(2,ret);
        ret.append("void callback").append(params != null ? "(" + params + ")" : "()");
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	tr = (CommonTree) child;
	            switch (tr.token.getType()) {
		            case ObjcParser.BLOCK_MULTI:
		            case ObjcParser.BLOCK_SINGLE:
		            	processBlock(tr,ret);
		            	break;
	                case ObjcParser.PARAMS:
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        }
        }
        iBlockCount = blockCount;
        newLines(1,ret);
    }

    Map<String,String> externs = new HashMap<String, String>();
    
    void processExtern(CommonTree tree, StringBuffer ret) {
    	//just add that to externs array
    	CommonTree ob = (CommonTree)tree.getFirstChildWithType(ObjcParser.FIELD_NAME);
    	if (ob != null) {
    		externs.put(ob.getChild(0).toString(), "");
    	}
    }
    
    void processDefine(CommonTree tree, StringBuffer ret) {
    	//TODO turn into int field for now
    	tree.token.setType(ObjcParser.FIELD);
    	CommonTree tr = new CommonTree(new CommonToken(ObjcLexer.TYPE_PLAIN, "int "));
    	tree.insertChild(0, tr); //before name
    	tr = new CommonTree(new CommonToken(ObjcLexer.OP, " = "));
    	tree.insertChild(2, tr); //after name
    	tr = new CommonTree(new CommonToken(ObjcLexer.LETTER, ";"));
    	tree.addChild(tr); //at the end
    	ret.append("final ");
    	processVarOrField(tree,ret);
    }
    
    private void addClassHeader(CommonTree tree, StringBuffer ret, String name) {
		ret.append("static class ");
		if (name == null) {
			Object o = tree.getFirstChildWithType(ObjcParser.TYPEDEF_NAME);
    		if (o != null) {
    			name  = o.toString();
    		}
		}
		if (name != null) {
			ret.append(name);
		}
		ret.append('{');
		iBlockCount++;
		newLines(2,ret);	
    }
    
    private void addClassFooter(CommonTree tree, StringBuffer ret) {
		iBlockCount--;
		newLines(1,ret);    		
		ret.append('}');
    }
    
    void processEnum(CommonTree tree, StringBuffer ret, String name) {
    	int type = tree.token.getType();
    	if (type == ObjcParser.ENUM_DEC) {
    		addClassHeader(tree,ret,name);
    	}
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	            	case ObjcParser.TYPE_PLAIN:
		            	parseTypePlain(tr,ret);
		            	break;
	            	case ObjcParser.TYPEDEF_NAME:
	            		//ignore
		            	break;
	                case ObjcParser.ENUM_FIELD:
	                	//turn it into final public int
	                	ret.append("final public int ");
	                	String str = tr.getChild(0).toString();
	                	ret.append(str);
	                	if (tr.getChildCount() > 2) {
	                		//pick operator and value
	                		str = tr.getChild(1).toString();
	                		String translation = keywordTranslation.get(str);
		                	ret.append(' ').append(translation != null ? translation : str).append(' ');
		                	parseValueInternal((CommonTree)tr.getChild(2),ret);
	                	}
	                	ret.append(';');
	                	newLines(1,ret);
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	iIgnorePlainNodesCurrledBrackets = true;
	        	processPlainNode(child,ret);
	        	iIgnorePlainNodesCurrledBrackets = false;
	        }
        }	    	    	    	
    	if (type == ObjcParser.ENUM_DEC) {
    		addClassFooter(tree,ret);
    	}
    }
    
    void processStructUnion(CommonTree tree, StringBuffer ret, String name) {
    	int type = tree.token.getType();
    	if (type == ObjcParser.STRUCT_DEC || type == ObjcParser.UNION_DEC) {
    		addClassHeader(tree,ret,name);
    		tree = (CommonTree)tree.getFirstChildWithType(ObjcParser.STRUCT);
    	}
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.FIELD:
	                	processVarOrField(tr,ret);
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	iIgnorePlainNodesCurrledBrackets = true;
	        	processPlainNode(child,ret);
	        	iIgnorePlainNodesCurrledBrackets = false;
	        }
        }	    	
    	if (type == ObjcParser.STRUCT_DEC || type == ObjcParser.UNION_DEC) {
    		addClassFooter(tree,ret);
    	}
    }

    void processTypedef(CommonTree tree, StringBuffer ret) {
    	String name  = null;
    	Object o = tree.getFirstChildWithType(ObjcParser.TYPEDEF_NAME);
    	if (o != null) {
    		name  = ((CommonTree)o).getChild(0).toString();	            		
    	}
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	            	case ObjcParser.TYPE_PLAIN:
	            		//TODO not exactly sure what to do here
		            	parseTypePlain(tr,ret);
		            	break;
	            	case ObjcParser.TYPEDEF_NAME:
	            		//ignore
		            	break;
	                case ObjcParser.ENUM_DEC:
	                	processEnum(tr,ret,name);
	                	break;
	                case ObjcParser.STRUCT_DEC:
	                case ObjcParser.UNION_DEC:
	                	processStructUnion(tr,ret,name);
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }	    	    	
    }

    void processSynchronized(CommonTree tree, StringBuffer ret) {
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.VALUE:
	                	parseValueInternal(tr,ret);
	                	break;
		            case ObjcParser.BLOCK_MULTI:
		            case ObjcParser.BLOCK_SINGLE:
		            	processBlock(tr,ret);
		            	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }	    	
    }
    
    void processTry(CommonTree tree, StringBuffer ret) {
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
		            case ObjcParser.BLOCK_MULTI:
		            case ObjcParser.BLOCK_SINGLE:
		            	ret.append(' ');
		            	processBlock(tr,ret);
		            	ret.append(' ');
		            	break;
	                case ObjcParser.CATCH_STMT:
	                	ret.append("catch (");
	                	processPlainNode(tr.getChild(0),ret);
	                	ret.append(' ');
	                	processPlainNode(tr.getChild(1),ret);
	                	ret.append(')');
	                	break;
	                case ObjcParser.FINALLY_STMT:
		            	ret.append(' ');
		            	processBlock((CommonTree)tr.getChild(0),ret);
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }	
    }
    
    void processThrow(CommonTree tree, StringBuffer ret) {
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
		            case ObjcParser.TYPE_PLAIN:
		            	parseTypePlain(tr,ret);
		            	ret.append(' ');
		            	break;
	                case ObjcParser.VALUE:
	                	ret.append(' ');
	                	parseValueInternal(tr,ret);
	                	break;
		            case ObjcParser.OP:
		            	ret.append(' ').append(tr.getChild(0).toString()).append(' ');
		            	break;
		            case ObjcParser.FIELD_NAME:
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }	
    }
    	
    String processStaticVar(CommonTree tree, StringBuffer ret) {
    	ret.append("static ");
    	return processVarOrField(tree,ret);
    }
    
    String processVarOrField(CommonTree tree, StringBuffer ret) {
    	//this could be classical function decl as well
    	int type = tree.token.getType();
    	if (type == ObjcLexer.VARIABLE) {
    		tree = (CommonTree)tree.getFirstChildWithType(ObjcLexer.FIELD);
    		if (tree == null) {
    			return null;
    		}
    	} else if (type != ObjcLexer.FIELD && type != ObjcLexer.STATIC_VARIABLE) {
    		return null;
    	}
    	String name = null;
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
                	case ObjcParser.ARRAY_INIT:
                		ret.append(" {");
                		iBlockCount++;
                		newLines(1,ret);
                		int i = 0;
                        for (Object val : tr.getChildren()) {
                        	String valStr = parseValue((CommonTree)val);
                        	if (valStr.length() > 0) {
                        		if (i++ > 0) {
                        			ret.append(',');
                                    newLines(1,ret);
                        		}
                        		ret.append(valStr);
                        	}
                        }
                		iBlockCount--;
                		newLines(1,ret);
                		ret.append('}');
                		break;
	                case ObjcParser.ENUM_DEC:
	                	processEnum(tr,ret,name);
	                	break;
	                case ObjcParser.UNION_DEC:
	                case ObjcParser.STRUCT_DEC:
	                	processStructUnion(tr,ret,name);
	                	break;
	                case ObjcParser.ENUM:
	                case ObjcParser.UNION:
	                case ObjcParser.STRUCT:
		            case ObjcParser.TYPE_PLAIN:
		            	parseTypePlain(tr,ret);
		            	ret.append(' ');
		            	break;
	                case ObjcParser.VALUE:
	                	//ret.append(' ');
	                	parseValueInternal(tr,ret);
	                	break;
		            case ObjcParser.PARAMS:
		            	String params = parseMethodParams(tr);
		            	iJavaCode.append('(').append(params).append(')');
		            	break;
		            case ObjcParser.BLOCK_MULTI:
		            	processBlock(tr,ret);
		            	break;
		            case ObjcParser.OP:
		            	ret.append(' ').append(tr.getChild(0).toString()).append(' ');
		            	break;
		            case ObjcParser.FIELD_NAME:
		            	name = tr.getChild(0).toString();
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else if (((CommonTree)child).token.getType() == ObjcParser.PARAMS) {
	        	ret.append("()");
	        } else {
	        	processPlainNode(child,ret);
	        }
        }
        return name;
    }
        
    void processVarAssignment(CommonTree tree, StringBuffer ret) {
    	int type = tree.token.getType();
    	if (type != ObjcLexer.VARIABLE_ASSIGNMENT) {
    		return;
    	}
    	
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
		            case ObjcParser.OBJECT_ACCESS:
		            	parseFallthroughNode(tr,ret);
		            	break;
	                case ObjcParser.VALUE:
	                	parseValueInternal(tr,ret);
	                	break;
		            case ObjcParser.OP:
		            	ret.append(' ').append(tr.getChild(0).toString()).append(' ');
		            	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	ret.append(' ');
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }	
    }
    
    void processDo(CommonTree tree, StringBuffer ret) {
    	int type = tree.token.getType();
    	if (type != ObjcLexer.SWITCH_STMT) {
    		return;
    	}
    	
    	newLines(1,ret);
    	ret.append("do ");
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.BLOCK_MULTI:
	                	processBlock(tr,ret);
	                	break;
	                case ObjcParser.BLOCK_SINGLE:
	                	iBlockCount++;
	                	newLines(1,ret);
	                	iBlockCount--;
	                	processInnerBlock(tr,ret);
	                	break;
	                case ObjcParser.VALUE:
	                	ret.append("while (");
	                	parseValueInternal(tr,ret);
	                	ret.append(')');
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }
    }
    
    void processSwitch(CommonTree tree, StringBuffer ret) {
    	int type = tree.token.getType();
    	if (type != ObjcLexer.SWITCH_STMT) {
    		return;
    	}
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.VALUE:
	                	ret.append("switch (");
	                	parseValueInternal(tr,ret);
	                	ret.append(") {");
	                	iBlockCount++;
	                	break;
	                case ObjcParser.CASE_STMT:
	                	//CASE_STMT->NAME|NUMBER->value
	                	newLines(1,ret);
	                	String value = ((CommonTree)tr.getChild(0)).getChild(0).toString();
	                	ret.append("case ").append(value).append(" :");
	                	break;
	                case ObjcParser.DEFAULT_STMT:
	                	newLines(1,ret);
	                	ret.append("default :");
	                case ObjcParser.BLOCK_MULTI:
	                	processBlock(tr,ret);
	                	break;
	                case ObjcParser.BLOCK_SINGLE:
	                	newLines(1,ret);
	                	processInnerBlock(tr,ret);
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }
        iBlockCount--;
        newLines(1,ret);
        ret.append('}');
    }
    
    void processWhile(CommonTree tree, StringBuffer ret) {
    	int type = tree.token.getType();
    	if (type != ObjcLexer.WHILE_STMT) {
    		return;
    	}
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.VALUE:
	                	ret.append(' ');
	                	parseValueInternal(tr,ret);
	                	ret.append(' ');
	                	break;
	                case ObjcParser.BLOCK_MULTI:
	                case ObjcParser.BLOCK_SINGLE:
	                	processBlock(tr,ret);
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }	
    }
    
    void processFor(CommonTree tree, StringBuffer ret) {
    	int type = tree.token.getType();
    	if (type != ObjcLexer.FOR_STMT) {
    		return;
    	}
    	//remove 1st child (the "for" node) to get right formating
    	tree.deleteChild(0);
    	ret.append("for ");
        for (Object child : tree.getChildren()) {
        	iIgnorePlainNodesNewLineOnSemicolon = true;
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
                	case ObjcParser.FIELD:
                		processVarOrField(tr,ret);
                		break;
                	case ObjcParser.FOR_IN_STMT:
	                	ret.append(" : ");
	                	String value = parseValue((CommonTree)tr.getChild(0));
	                	ret.append(value);
                		break;
	                case ObjcParser.VALUE:
	                	ret.append(' ');
	                	parseValueInternal(tr,ret);
	                	ret.append(' ');
	                	break;
	                case ObjcParser.OBJECT_ACCESS:
	                	ret.append(' ');
	                	parseFallthroughNode(tr,ret);
	                	ret.append(' ');
	                	break;
	                case ObjcParser.BLOCK_MULTI:
	                case ObjcParser.BLOCK_SINGLE:
	                	iIgnorePlainNodesNewLineOnSemicolon = false;
	                	processBlock(tr,ret);
	                	break;
	                case ObjcParser.NAME:
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }
    	iIgnorePlainNodesNewLineOnSemicolon = false;
    }
    
    void processReturn(CommonTree tree, StringBuffer ret) {
    	if (iCurrentMethodName != null && iCurrentMethodName.equals(iCurrentClassName)) {
    		//ignore return statement for constructors and remove following ';'
    		CommonTree tr = (CommonTree)tree.getParent();
    		int idx = tree.getChildIndex();
    		if (tr.getChildCount() > ++idx) {
    			tr = (CommonTree)tr.getChild(idx);
    			tr.token.setText("");
    		}
    		return;
    	}
    	tree = (CommonTree)tree.getFirstChildWithType(ObjcLexer.VALUE);
    	if (tree != null) {
        	ret.append("return ");
        	parseValueInternal(tree,ret);
    	} else {
    		ret.append("return;");
    		newLines(1,ret);
    	}
    }

    void processIfElse(CommonTree tree, StringBuffer ret) {
    	int type = tree.token.getType();
    	if (type == ObjcLexer.ELSE_STMT) {
    		ret.append(" else");
    	} else if (type == ObjcLexer.IF_STMT) {
    		ret.append("if (");    		
    	} else {
    		return;
    	}
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.VALUE:
	                	parseValueInternal(tr,ret);
	                	ret.append(')');
	                	break;
	                case ObjcParser.BLOCK_MULTI:
	                case ObjcParser.BLOCK_SINGLE:
	                	processBlock(tr,ret);
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }
    }
    
    static class MethodInfo {
    }
    
    static class PropertyInfo extends MethodInfo {
    	String iName; //!= null only if name is different from property => need to replace variable
    	int iGetVarStart;
    	int iGetVarEnd;
    	int iSetVarStart;
    	int iSetVarEnd;
    }
    
    static class InterfaceInfo {
    	int retMark;
    	int endMark;
    	Map<String,MethodInfo> iMethods = new HashMap<String,MethodInfo>(); //declared public methods
    }
    
    private Map<String, InterfaceInfo> interfaces = new HashMap<String, InterfaceInfo>();

	void revisitInterfaceMarks(int insertedTextLen, InterfaceInfo currentInfo) {
		//move my own (===) endMark all other marks (>) if they are after my mark
		//move my own mark not to reorder methods
		for (Map.Entry<String,InterfaceInfo> e : interfaces.entrySet()) {
			InterfaceInfo info = e.getValue();
			if (info.endMark >= currentInfo.retMark) {
				info.endMark += insertedTextLen;
			}
			if (info.retMark > currentInfo.retMark) {
				info.retMark += insertedTextLen;
			}
			for (Map.Entry<String,MethodInfo> m : info.iMethods.entrySet()) {
				MethodInfo mi = m.getValue();
				if (PropertyInfo.class.isInstance(mi)) {
					PropertyInfo pi = (PropertyInfo)mi;
					if (pi.iGetVarStart >= currentInfo.retMark) {
						pi.iGetVarStart += insertedTextLen;
						pi.iGetVarEnd += insertedTextLen;
					}
					if (pi.iSetVarStart >= currentInfo.retMark) {
						pi.iSetVarStart += insertedTextLen;
						pi.iSetVarEnd += insertedTextLen;
					}
				}
			}
		}
	}
	
    void processSynthetize(CommonTree tree, StringBuffer ret) {
    	//TODO the way the @property / @synthetize business is done doesn't work for custom methods yet
        InterfaceInfo inf = interfaces.get(iCurrentClassName);
        if (inf == null) {
        	return;
        }
        tree = (CommonTree)tree.getFirstChildWithType(ObjcParser.PROPERTY);
        if (tree.getChildCount() == 2) {
        	String name = tree.getChild(0).toString();
        	MethodInfo info = inf.iMethods.get(name);
        	if (info != null && PropertyInfo.class.isInstance(info)) {
        		PropertyInfo p = (PropertyInfo)info;
        		p.iName = tree.getChild(1)/*NAME*/.getChild(0).toString();
        	}
        }
    }

    void processProperty(CommonTree tree, StringBuffer ret) {

    	newLines(2,ret);
        String type = null;
        String name = null;
        for (Object child : tree.getChildren()) {
            switch (((CommonTree) child).token.getType()) {
                case ObjcParser.TYPE_PLAIN:
                	StringBuffer b = new StringBuffer();
	            	parseTypePlain((CommonTree) child,b);
                    type = b.toString();
                    break;
                case ObjcParser.FIELD_NAME:
                    name = ((CommonTree) child).getChild(0).toString();
                    break;
            }
        }
        PropertyInfo info = new PropertyInfo();
        
        ret.append("//TODO CONV conversion from @property");
        newLines(1,ret);
        //add both getter & setter
        String transType = keywordTranslation.get(type);
        type = transType != null ? transType : type;
        ret.append("public ").append(type).append(" ");
        ret.append(name);
        ret.append("() {return ");
        info.iGetVarStart = ret.length();
        info.iGetVarEnd = info.iGetVarStart + name.length();
        ret.append(name).append(";}");
        newLines(1,ret);
        ret.append("public void set").append(name).append("(").append(type).append(" a").append(name).append(") {");
        info.iSetVarStart = ret.length();
        info.iSetVarEnd = info.iSetVarStart + name.length();
        ret.append(name).append(" = a").append(name).append(";}");
        
        InterfaceInfo inf = interfaces.get(iCurrentClassName);
        //this must not be null as we are inside of an interface righ now
        inf.iMethods.put(name, info);

        newLines(1,ret);
    }

    private void processInnerBlock(CommonTree tree, StringBuffer ret) {

    	for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
                	case ObjcParser.BLOCK_MULTI:
                		processBlock(tr,ret);
                		break;
	                case ObjcParser.METHOD_CALL:
	                	String method = translateClassicalMethod(tr);
	                	ret.append(method);
	                	break;
	                case ObjcParser.METHOD_MSG:
	                	translateMethodMsg(tr,ret);
	                	break;
	                case ObjcParser.SYNCHRONIZED:
	                	processSynchronized(tr, ret);
	                	break;
	                case ObjcParser.TRY_STMT:
	                	processTry(tr, ret);
	                	break;
	                case ObjcParser.THROW_STMT:
	                	processThrow(tr, ret);
	                	break;
	                case ObjcParser.STATIC_VARIABLE:
	                	processStaticVar(tr, ret);
	                	break;
	                case ObjcParser.VARIABLE:
	                	processVarOrField(tr, ret);
	                	break;
	                case ObjcParser.VARIABLE_ASSIGNMENT:
	                	processVarAssignment(tr, ret);
	                	break;
	                case ObjcParser.DO_STMT:
	                	processDo(tr, ret);
	                	break;
	                case ObjcParser.WHILE_STMT:
	                	processWhile(tr, ret);
	                	break;
	                case ObjcParser.IF_STMT:
	                	processIfElse(tr, ret);
	                	break;
	                case ObjcParser.ELSE_STMT:
	                	processIfElse(tr, ret);
	                	break;
	                case ObjcParser.SWITCH_STMT:
	                	processSwitch(tr, ret);
	                	break;
	                case ObjcParser.RETURN_STMT:
	                	processReturn(tr, ret);
	                	break;
	                case ObjcParser.FOR_STMT:
	                	processFor(tr, ret);
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;
	            }
	        } else if (CommonTree.class.isInstance(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.BREAK_STMT:
	                	ret.append("break;");
	                	break;
	                case ObjcParser.CONTINUE_STMT:
	                	ret.append("continue;");
	                	break;
	                default:
	                	parseFallthroughNode(tr,ret);
	                	break;	            
	            }
	        } else {
	        	processPlainNode(child,ret);
	        }
        }
	
    }
    
    void processBlock(CommonTree tree, StringBuffer ret) {
    	int type = tree.token.getType();
    	if (type != ObjcLexer.BLOCK_SINGLE &&
    			type != ObjcLexer.BLOCK_MULTI) {
    		return;
    	}
    	CommonTree ancsElse = (CommonTree)tree.getParent();
    	if (type == ObjcLexer.BLOCK_SINGLE && tree.getFirstChildWithType(ObjcLexer.IF_STMT) != null &&
    			ancsElse.token.getType() == ObjcLexer.ELSE_STMT) {
    		//'else' followed by 'if' statement
    		ret.append(' ');
    		processInnerBlock(tree,ret);
    	} else {
	    	iBlockCount++;
	    	if (type == ObjcLexer.BLOCK_SINGLE) {
	    		newLines(1,ret);
		    	iBlockCount--;
		    	processInnerBlock(tree,ret);
	    	} else {
	    		//first child node here is '{' => add it and remove the node
    			int ch;
	    		if ((ch = ret.charAt(ret.length() - 1)) != '\n' && ch != '\t') {
	    			ret.append(' ');
	    		}
    			ret.append('{');
	    		newLines(1,ret);
	    		tree.deleteChild(0);
		    	processInnerBlock(tree,ret);
		    	iBlockCount--;
		    	//check if else follows
		    	int n = 1;
		    	if (ancsElse.token.getType() == ObjcLexer.IF_STMT) {
			    	int idx = ancsElse.getChildIndex();
		    		tree = (CommonTree)ancsElse.getParent();
			    	if (tree.getChildCount() > ++idx) {
			    		Object o = tree.getChild(idx);
			    		if (CommonTree.class.isInstance(o) && ((CommonTree)o).token.getType() == ObjcLexer.ELSE_STMT) {
			    			//else follows => undo possible new line added when closing previous block
			    			while ((ch = ret.charAt(ret.length() - 1)) == '\n' || ch == '\t') {
			    				ret.deleteCharAt(ret.length() - 1);
			    			}
			    			n = 0;
			    		}
			    	}
		    	}
		    	if (n > 0) {
		    		newLines(n,ret);
		    	}
	    	}
    	}
    }

    void translateMethodImplementationOrDeclaration(CommonTree tree) {
    	int type = tree.token.getType();
    	if (type != ObjcLexer.METHOD_IMPL && type != ObjcLexer.METHOD) {
    		return;
    	}
    	if (type != ObjcLexer.METHOD_IMPL) {
    		//newLines(1,iJavaCode);
    	} else {
    		iCurrentMethodBlockCount = iBlockCount;
    		iCurrentMethodMark = iJavaCode.length();
    		newLines(1,iJavaCode);
    	}
    	
    	int retMark = 0;
    	String modifier = "";
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	            	case ObjcParser.MODIFIER:
	            		String name = tr.getChild(0).toString();
	            		if (name.equals("+")) {
		            		modifier = "static ";	            			
	            		}
	            		break;
	                case ObjcParser.TYPE_PLAIN:
	                	retMark = iJavaCode.length();
	                	parseTypePlain(tr, iJavaCode);
	                	break;
	                case ObjcParser.METHOD_NAME:
	            		name = tr.getChild(0).toString();
	            		InterfaceInfo info = interfaces.get(iCurrentClassName);
	            		//info.iMethods holds original method name
	            		String scope = ""; //default
	            		if (info != null) {
		            		scope = info.iMethods.get(name) != null ? "public " : "private ";
	            		}
	            		if (scope.length() > 0) {
		            		//insert in front of the type
	            			iJavaCode.insert(retMark,scope);
	            			retMark += scope.length();
	            		}
	            		//insert in front of the type
	            		iJavaCode.insert(retMark,modifier);
            			retMark += modifier.length();
	            		if (name.startsWith("init")) {
	            			String retType = iJavaCode.substring(retMark, iJavaCode.length());
	            			if (retType.equals("Object")) {
		            			//strip return type and turn it to constructor
	            				iJavaCode.delete(retMark, iJavaCode.length());
		            			name = iCurrentClassName;
	            			}
	            		}
            			iCurrentMethodName = name;
            			iJavaCode.append(" ").append(name);
	            		if (tree.getFirstChildWithType(ObjcParser.METHOD_PARAMS) == null) {
	            			//no params
	            			iJavaCode.append("()");
	            		}
	                	break;
	                case ObjcParser.METHOD_PARAMS:
	                	String params = parseMethodParams(tr);
	                	iJavaCode.append('(').append(params).append(')');
	                	break;
	                case ObjcParser.BLOCK_MULTI:
	                	processBlock(tr,iJavaCode);
	                	break;
	                default:
	                	parseFallthroughNode(tr,iJavaCode);
	                	break;
	            }
	        }
        }
    	if (type != ObjcLexer.METHOD_IMPL) {
    		iJavaCode.append(';'); //no processPlainNode here
            newLines(1,iJavaCode);
    	}
        iCurrentMethodMark = 0;
    }
        
    void parseImplementation(CommonTree tree) {
    	if (tree.token.getType() != ObjcLexer.IMPLEMENTATION) {
    		return;
    	}
    	
    	int prevLen = 0;
    	InterfaceInfo info = null;
    	String fragment = null;
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	            	case ObjcParser.NAME: //always present
	            		//attempt to find the class previously defined using INTERFACE token type
	            		iCurrentClassName = tr.getChild(0).toString();
	            		info = interfaces.get(iCurrentClassName);
	            		if (info != null) {
	            			//split ret at retPos to complete previously declared class
	            			fragment = iJavaCode.substring(info.retMark);
	            			iJavaCode.delete(info.retMark,iJavaCode.length());
	            			prevLen = iJavaCode.length();
	            		} else {
	            	    	newLines(3,iJavaCode);
	            			iJavaCode.append("public class ").append(iCurrentClassName).append(" {");
		                	newLines(1,iJavaCode);
	            		}
	                	iBlockCount++;
	                	newLines(1,iJavaCode);
	            		break;
	                case ObjcParser.METHOD_IMPL:
	                	translateMethodImplementationOrDeclaration(tr);
	                	break;
	                case ObjcParser.SYNTHETIZE:
	                	processSynthetize(tr, iJavaCode);
	                	break;
	                case ObjcParser.EXTERN:
	                	processExtern(tr, iJavaCode);
	                	break;
	                case ObjcParser.DEFINE:
	                	processDefine(tr, iJavaCode);
	                	break;
	                case ObjcParser.TYPEDEF:
	                	processTypedef(tr, iJavaCode);
	                	break;
	                case ObjcParser.STATIC_VARIABLE:
	                	processStaticVar(tr, iJavaCode);
	                	break;
	                case ObjcParser.CATEGORY:
	                	//ignore
	                	break;
	                default:
	                	parseFallthroughNode(tr,iJavaCode);
	                	break;
	            }
	        } else if (fragment == null || !child.toString().equals("@end")) {
	        	//ignore @end alias '}' if we completing previously declred class
	        	processPlainNode(child,iJavaCode);
	        }
        }
        iCurrentClassName = null;
        if (fragment != null) {
        	revisitInterfaceMarks(iJavaCode.length() - prevLen, info);
        	iJavaCode.append(fragment);
        	fragment = null;
        }
        iBlockCount--;
    }
    
    void addIMPLEMENTS_INTERFACESandFinishHeader(CommonTree tree, StringBuffer ret) {
		if (tree != null) {
			String implementsIntf = null;
	        for (Object o : tree.getChildren()) {
	        	CommonTree intr = (CommonTree)o;
	        	if (implementsIntf != null) {
	        		ret.append(',');
	        	} else {
	        		ret.append("implements ");		        	        		
	        	}
	    		implementsIntf = intr.getChild(0).toString();
	    		ret.append(implementsIntf).append(' ');
	        }
		}
		ret.append('{');
		iBlockCount++;
		newLines(2,ret);
    }
    
    void parseInterfaceDec(CommonTree tree) {
    	if (tree.token.getType() != ObjcLexer.INTERFACE) {
    		return;
    	}
    	//ignore categories
    	CommonTree chld = (CommonTree)tree.getFirstChildWithType(ObjcParser.INTERFACE_NAME);
    	if (chld != null && chld.getFirstChildWithType(ObjcParser.CATEGORY) != null) {
    		return;
    	}
    	newLines(2,iJavaCode);
    	
    	InterfaceInfo info = null;
    	String scope = ""; //default is package
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.INTERFACE_NAME: //always present
	            		iCurrentClassName = tr.getChild(0).toString();
	            		iJavaCode.append(" ").append(iCurrentClassName);
		            	info = new InterfaceInfo();
		            	interfaces.put(iCurrentClassName, info);
	                	break;
	                case ObjcParser.SUPERCLASS_NAME: //always present
	                	Object ob = tr.getChild(0);
	                	CommonTree ctr = null;
	                	iJavaCode.append(' ');
	                	if (CommonTree.class.isInstance(ob) && ((CommonTree)ob).token.getType() == ObjcParser.IMPLEMENTS_INTERFACES) {
	                		//no superclass name only interfaces
	                		ctr = (CommonTree)ob;
	                	} else {
		                	String name = tr.getChild(0).toString();
		                	iJavaCode.append("extends ").append(name).append(' ');
	                		ctr =(CommonTree) tr.getFirstChildWithType(ObjcParser.IMPLEMENTS_INTERFACES);
	                	}
	                	addIMPLEMENTS_INTERFACESandFinishHeader(ctr,iJavaCode);
	                	break;
	                case ObjcParser.GROUP_MODIFIER:
	                	scope = tr.getChild(0).toString();
	                	scope = keywordTranslation.get(scope);
	                	break;
	                case ObjcParser.TYPEDEF:
	                	processTypedef(tr,iJavaCode);
	                	break;
	                case ObjcParser.VARIABLE:
	                case ObjcParser.FIELD:
	                	processVarOrField(tr,iJavaCode);
	                	break;
	                case ObjcParser.PROPERTY:
	                	//turn it into a public method
	                	processProperty(tr,iJavaCode);
	                	break;
	                case ObjcParser.METHOD:
	                	//ignore method declaration it will be added with implementation
	                	//only remember then to be able to set a scope properly
	                	CommonTree ntr = (CommonTree)tr.getFirstChildWithType(ObjcParser.METHOD_NAME);
	                	String methodName = ntr.getChild(0).toString();
	                	info.iMethods.put(methodName, new MethodInfo()); //TODO this isn't ideal as it makes all method of the same name public
	                	// signature regardless but what the hell....
	                	break;
	                default:
	                	parseFallthroughNode(tr,iJavaCode);
	                	break;
	            }
	        } else {
	        	iIgnorePlainNodesCurrledBrackets = true;
	        	processPlainNode(child,iJavaCode);
	        	iIgnorePlainNodesCurrledBrackets = false;
	        }
        }
    	//and remember class name declaration position to complete it when we find implementation
    	info.retMark = iJavaCode.length();
    	info.endMark = iJavaCode.length();
    	//add closing bracket
    	iBlockCount--;
    	newLines(1,iJavaCode);
    	iJavaCode.append('}');
    	newLines(1,iJavaCode);
    }

    void parseProtocolDec(CommonTree tree) {
    	if (tree.token.getType() != ObjcLexer.PROTOCOL) {
    		return;
    	}
    	newLines(2,iJavaCode);
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.PROTOCOL_NAME: //always present
	            		iCurrentClassName = tr.getChild(0).toString();
	            		iJavaCode.append(' ').append(iCurrentClassName).append(' ');
                		Object ctr = tree.getFirstChildWithType(ObjcParser.IMPLEMENTS_INTERFACES);
                		if (ctr == null) {
                			addIMPLEMENTS_INTERFACESandFinishHeader(null,iJavaCode);
                		}
	                	break;
	                case ObjcParser.IMPLEMENTS_INTERFACES: //always present
	                	addIMPLEMENTS_INTERFACESandFinishHeader(tr,iJavaCode);
	                	break;
	                case ObjcParser.METHOD:
	                	//all method of interface are made public
	                	iJavaCode.append("public ");
	                	translateMethodImplementationOrDeclaration(tr);
	                	break;
	                default:
	                	parseFallthroughNode(tr,iJavaCode);
	                	break;
	            }
	        } else {
	        	iIgnorePlainNodesCurrledBrackets = true;
	        	processPlainNode(child,iJavaCode);
	        	iIgnorePlainNodesCurrledBrackets = false;
	        }
        }
    	iBlockCount--;
    	newLines(1,iJavaCode);
    	iJavaCode.append('}');
    	newLines(1,iJavaCode);
    }

    void parseCode(CommonTree tree) {
        for (Object child : tree.getChildren()) {
        	if (isNodeWithChildern(child)) {
	        	CommonTree tr = (CommonTree) child;
	            switch (tr.token.getType()) {
	                case ObjcParser.FORWARD_DECLARATION:
	                	//TODO forward declaration => ignore4now could be used to generate #import directive
	                	break;
	                case ObjcParser.IMPLEMENTATION:
	                	parseImplementation(tr);
	                	break;
	                case ObjcParser.PROTOCOL:
	                	parseProtocolDec(tr);
	                	break;
	                case ObjcParser.INTERFACE:
	                	parseInterfaceDec(tr);
	                	break;
	                case ObjcParser.STATIC_VARIABLE:
	                case ObjcParser.VARIABLE:
	                	iBlockCount = 2;
	                	int n = iJavaCodeGlobal.length();
	                	String name = tr.token.getType() == ObjcParser.STATIC_VARIABLE ? processStaticVar(tr, iJavaCodeGlobal) : processVarOrField(tr, iJavaCodeGlobal);
	                	if (externs.get(name) != null) {
	                		iJavaCodeGlobal.insert(n, "public ");
	                	}
	                	iBlockCount = 0;
	                	break;
	                case ObjcParser.EXTERN:
	                	iBlockCount = 2;
	                	processExtern(tr, iJavaCodeGlobal);
	                	iBlockCount = 0;
	                	break;
	                case ObjcParser.DEFINE:
	                	iBlockCount = 2;
	                	processDefine(tr, iJavaCodeGlobal);
	                	iBlockCount = 0;
	                	break;
	                case ObjcParser.TYPEDEF:
	                	iBlockCount = 2;
	                	processTypedef(tr, iJavaCodeGlobal);
	                	iBlockCount = 0;
	                	break;
	                default:
	                	iBlockCount = 2;
	                	parseFallthroughNode(tr,iJavaCodeGlobal);
	                	iBlockCount = 0;
	                	break;
	            }
	        } else {
            	iBlockCount = 2;
	        	processPlainNode(child,iJavaCodeGlobal);
            	iBlockCount = 0;
	        }
        }
    }

    boolean processInput(String input)  throws IOException, RecognitionException {
        CharStream cs = new ANTLRStringStream(input);
        ObjcLexer lexer = new ObjcLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream();
        tokens.setTokenSource(lexer);
        ObjcParser parser = new ObjcParser(tokens);

        ObjcParser.code_return result = parser.code();
	
        CommonTree root = (CommonTree) result.getTree();
        if (root.token.getType() != ObjcLexer.CODE) {
        	root =  (CommonTree) root.getFirstChildWithType(ObjcLexer.CODE);
        }
        if (root != null) {
        	iBlockCount = 0;
        	parseCode(root);
        }
        return root != null;
    }
    
    boolean processFile(File f) throws IOException, RecognitionException {
        String input;
        FileInputStream in;
    	input = ConverterObjc2Java.stringFrom((in = new FileInputStream(f)));
    	in.close();
        input = input.replace("///", "//");
        return processInput(input);
    }
    
    void synthetizeProperties() {
		for (Map.Entry<String,InterfaceInfo> e : interfaces.entrySet()) {
			InterfaceInfo info = e.getValue();
			for (Map.Entry<String,MethodInfo> m : info.iMethods.entrySet()) {
				MethodInfo mi = m.getValue();
				if (PropertyInfo.class.isInstance(mi)) {
					PropertyInfo pi = (PropertyInfo)mi;
					if (pi.iName != null) {
				    	int diff = pi.iName.length() - (pi.iGetVarEnd - pi.iGetVarStart);
						iJavaCode.replace(pi.iGetVarStart, pi.iGetVarEnd, pi.iName);
						//set is behind get
						iJavaCode.replace(pi.iSetVarStart + diff, pi.iSetVarEnd + diff, pi.iName);
						int mark = info.retMark; 
						info.retMark = pi.iSetVarEnd + diff + 1;
						revisitInterfaceMarks(diff, info);
						info.retMark = mark;
					}
				}
        	}
        }
    }
    
    void insertGlobalScope() {
        if (iJavaCodeGlobal.length() > 0) {
        	iBlockCount = 1;
        	newLines(1,iJavaCodeGlobal);
        	iJavaCodeGlobal.append('}');
        	iBlockCount = 0;
        	newLines(2,iJavaCodeGlobal);	
        	InterfaceInfo info = interfaces.get(iGlobalScope);
        	if (info == null) {
        		//insert in front of methods of the 1st class we find
        		Collection<InterfaceInfo> c = interfaces.values();
        		Object[] a = c.toArray();
        		if (a.length > 0) {
        			info = (InterfaceInfo)a[0];
        		}        		
        	}
        	if (info != null) {
        		iJavaCode.insert(info.retMark, iJavaCodeGlobal);
        	} else {
        		info = new InterfaceInfo();
        		info.retMark = 0;
        		info.endMark = 0;
        		iJavaCode.insert(0, iJavaCodeGlobal);        		
        	}
    		revisitInterfaceMarks(iJavaCodeGlobal.length(),info);
    		iJavaCodeGlobal.delete(0, iJavaCodeGlobal.length());
        }
    }

    void declareGlobalScope(String fileName) {
    	//TODO adds <fileName>Global to the resulting java where all the global scope stuff goes
    	//don't really know what else to do here
    	newLines(2,iJavaCodeGlobal);
    	iBlockCount = 1;
    	newLines(0,iJavaCodeGlobal);
    	iJavaCodeGlobal.append("public static ").append("Global {");
    	iBlockCount++;
    	newLines(1,iJavaCodeGlobal);
    	iGlobalScope = fileName + "_Global";
//    	InterfaceInfo info = new InterfaceInfo();
//    	info.retMark = iJavaCodeGlobal.length();
//    	interfaces.put(iGlobalScope, info);
    	iBlockCount = 0;
    }
        
    public static boolean convert_m(String fileName/*without extension*/, String inDir, String outDir) throws IOException, RecognitionException {
    	//takes files in pairs (.m/.mm and .h/.hpp )
    	//if it finds .h/.hpp only nothing happens
    	//directories must to end with '/'
    	//TODO doesn't really cope with C++ declarations
    	String filePathName = inDir + fileName + ".";
        File mfile = new File(filePathName + "m");
        if (!mfile.exists()) {
        	mfile = new File(filePathName + "mm");
            if (!mfile.exists()) {
            	return false;
            }
        }
        File hfile = new File(filePathName + "h");
        if (!hfile.exists()) {
        	hfile = new File(filePathName + "hpp");
        }
        ConverterObjc2Java cnv = new ConverterObjc2Java();
        cnv.newLines(2, cnv.iJavaCode);
        //TODO append most frequently used #import
        
        cnv.declareGlobalScope(fileName);
        if (hfile.exists()) {
        	//process header file 1st
            if (!cnv.processFile(hfile)) {
            	return false;
            }
        }
    	//process m file
        if (!cnv.processFile(mfile)) {
        	return false;
        }
        cnv.insertGlobalScope();
        cnv.synthetizeProperties();

        String javaCode = cnv.iJavaCode.toString();
        //replace ;; by ;
        javaCode = javaCode.replaceAll(";(\\s|\n|\r)*;+", ";");

        //TODO there's a comment in parseValue about this
        //remove "this = null"
        javaCode = javaCode.replaceAll("this\\s*=\\s*null", "");
        //remove "this ="?
        //javaCode = javaCode.replaceAll("this\\s*=\\s*", "");

        File foutDir = new File(outDir);
        foutDir.mkdirs();
        File jfile = new File(outDir + fileName + '.' + "java");
        FileOutputStream out = new FileOutputStream(jfile);
        appendStringTo(javaCode, out);
        out.close();
        return true;
    }

	public static final int COPY_BUFFER_SIZE = 2048; //could be tuned to maximize the transfer speed
	
	static String stringFrom(InputStream aIn) throws IOException {
		if (aIn != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[COPY_BUFFER_SIZE];
			Reader reader = new InputStreamReader(aIn, Charset.forName("utf-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
			return writer.toString();
		}
		return null;
	}

	static void appendStringTo(String aS, OutputStream aOut) throws IOException {
		if (aOut != null) {
			Reader reader = new StringReader(aS);
			char[] buffer = new char[COPY_BUFFER_SIZE];
			Writer writer = new OutputStreamWriter(aOut, Charset.forName("utf-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
			writer.close();
			aOut.flush();
		}
	}
}
