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
	boolean iPlainNodesSlashAsSemicolon = false;
//	boolean iNoNewLines = false;
	
	String iGlobalScope = null;
	//TODO the way we work with the below 2 vars assumes no nested classes and/or methods which is fine for objc
	String iCurrentClassName = null;
	String iCurrentMethodName = null;
	String iCurrentMethodModifier = "";
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
    	methodTranslation.put("mutableCopy", "");
    	methodTranslation.put("release", " = null"); //the leading space is important here
    	methodTranslation.put("autorelease", "");
    	methodTranslation.put("isKindOfClass", "<<isInstance"); //<< => swap value and parameter
    	methodTranslation.put("class()", "class");
    	methodTranslation.put("integerValue", "intValue");
    	//dictionary->map methods
    	methodTranslation.put("objectForKey", "get");
    	methodTranslation.put("setObject", ">>put"); //>> => swap parameters
    	methodTranslation.put("valueForKey", "get");
    	methodTranslation.put("removeObjectForKey","remove");
    	methodTranslation.put("removeAllObjects","clear"); //works for Vector/List as well
    	methodTranslation.put("allKeys","keySet");    	
    	//array->map methods
    	methodTranslation.put("objectAtIndex", "get");
    	methodTranslation.put("addObject", "add");
    	methodTranslation.put("removeObject", "remove");
    	methodTranslation.put("removeObjectAtIndex", "removeElementAt");
    	methodTranslation.put("replaceObjectAtIndex", "set");
    	//methodTranslation.put("removeAllObjects","removeAll");
    	//object->object methods
    	methodTranslation.put("isEqual", "equals");
    	//NSString -> String methods
    	methodTranslation.put("isEqualToString", "equals");
    	methodTranslation.put("NSAssert","Assert.assertTrue");
    	methodTranslation.put("assert","Assert.assertFalse");
    	methodTranslation.put("stringWithFormat", "format");
    	methodTranslation.put("appendString","+=");
    	methodTranslation.put("appendFormat"," += String.format"); //leading space is important
    	methodTranslation.put("stringByAppendingString","concat");
    	methodTranslation.put("stringByAppendingFormat"," + String.format"); //the leading space is important here
    	methodTranslation.put("stringByReplacingOccurrencesOfRegex","replace");
    	methodTranslation.put("stringByReplacingAll","replaceAll");
    	methodTranslation.put("substringFromIndex","substring");
    	methodTranslation.put("substringToIndex","substring(0,"); //ending ',' means the rest of the parameters follow
    	methodTranslation.put("characterAtIndex","charAt");
    	methodTranslation.put("hasPrefix","startsWith");
    	methodTranslation.put("hasSuffix","endsWith");
    	methodTranslation.put("componentsSeparatedByString","split");
    	methodTranslation.put("isMatchedByRegex","matches");
    	//MH specific translations
    	methodTranslation.put("MHUtils_MHLog_I","MHUtils.MHLog.i");
    	methodTranslation.put("lastPathComponent","<<MHUtils.MHString.lastPathComponent"); //<< => swap value and parameter
    	methodTranslation.put("timeIntervalSince1970","<<MHUtils.MHDate.timeIntervalSince1970");
    	methodTranslation.put("stringByDeletingPathExtension","<<MHUtils.MHString.stringByDeletingPathExtension");
    };

    private static final Map<String, String> keywordTranslation;
    static {
    	keywordTranslation = new HashMap<String, String>();
    	keywordTranslation.put("nil", "null");
    	keywordTranslation.put("NULL", "null");
    	keywordTranslation.put("YES", "true");
    	keywordTranslation.put("NO", "false");
    	keywordTranslation.put("#define", "/*#define*/");    	
    	keywordTranslation.put("@selector", "this.getClass().getMethod"); //this need manual completion as it needs a look at the method params to complete method signature
    	keywordTranslation.put("id", "Object"); //for lack of anything else
    	keywordTranslation.put("NSObject", "Object"); //TODO convert stuff like (void\\s\*) to Object
    	keywordTranslation.put("NSDictionary", "Map<Object,Object>"); //this needs manual when it comes to calls like NSDictionary.class
    	keywordTranslation.put("NSMutableDictionary", "Map<Object,Object>");
    	keywordTranslation.put("NSString", "String");
    	keywordTranslation.put("NSMutableString", "String");
    	keywordTranslation.put("NSRecursiveLock", "ReentrantLock");
    	keywordTranslation.put("NSLock", "Lock");
    	keywordTranslation.put("NSNumber", "Number");
    	keywordTranslation.put("NSInteger", "int");
    	keywordTranslation.put("NSUInteger", "unsigned int");
    	keywordTranslation.put("NSArray", "Vector<Object>");
    	keywordTranslation.put("NSMutableArray", "Vector<Object>");
    	keywordTranslation.put("NSException", "Exception");
    	keywordTranslation.put("NSDate", "Date");
    	keywordTranslation.put("BOOL", "boolean");
    	keywordTranslation.put("self", "this");
    	keywordTranslation.put("IBAction", "void");    	
    	keywordTranslation.put("->", ".");
    	keywordTranslation.put("&", "");
    	keywordTranslation.put("\n", "");
    	keywordTranslation.put("extern", "public");
    	keywordTranslation.put("const", "final"); //TODO this may result in 'final' being placed after <type> which is invalid in java
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
    	//the 2 translation below is a bit simplification but hey.....
    	keywordTranslation.put("count","size()");
    	//keywordTranslation.put("length","length()"); this one is too much
    	//simplification end...
    	keywordTranslation.put("\"C\"", "");
    	//Android specific translation
    	keywordTranslation.put("viewDidLoad", "@Override\nonCreate");
    	keywordTranslation.put("viewWillAppear", "@Override\nonStart");
    	keywordTranslation.put("viewDidAppear", "@Override\nonResume");
    	keywordTranslation.put("viewWillDisappear", "@Override\nonPause");
    	keywordTranslation.put("viewDidDisappear", "@Override\nonStop");
    	keywordTranslation.put("viewDidUnload", "@Override\nonDestroy");
    	//MH specific translation
    	keywordTranslation.put("MHSystem_File","File");
    	keywordTranslation.put("NSError", "MHUtils.MHError");
    	keywordTranslation.put("NSNotFound", "MHConstants.MHNotFound");
    	
    };

    String translateMethod(String name) {
		String tname = methodTranslation.get(name);
		if (tname != null) {
			int i = tname.indexOf('\n');
			if (i > -1) {
				tname = tname.substring(i + 1);
			}
			name = tname;
		}
		return name;
    }
    
    String translateKeyword(String name) {
		String tname = keywordTranslation.get(name);
		if (tname != null) {
			int i = tname.indexOf('\n');
			if (i > -1) {
				tname = tname.substring(i + 1);
			}
			name = tname;
		}
		//MH specific bit=>replace '_' by '.' to change global stuff into a member of a class
		//don't replace '_' after 2 consecutive capital letters
		int i = name.indexOf('_');
		//check if the first part matches any class we've got in this file, if yes remove it
		if (i > 0) {
			String s = name.substring(0,i);
			if (interfaces.get(s) != null) {
				name = name.substring(i + 1);
				i = name.indexOf('_');
			}
		}
		while (i > 0 && name.substring(i - 1, i + 1).matches("[a-z0-9]_")) {
			name = name.substring(0,i) + '.' + name.substring(i + 1);
			i = name.indexOf('_');
		}
		return name;
    }

    void newLines(int count,StringBuffer ret) {
		while (count-- > 0) {
			ret.append('\n');
		}
		int b = iBlockCount;
		while (b-- > 0) {
			ret.append('\t');
		}
    }
    
    void undoNewLines(StringBuffer ret) {
    	char ch;
		while (ret.length() > 0 && ((ch = ret.charAt(ret.length() - 1)) == '\n' || ch == '\t')) {
			ret.deleteCharAt(ret.length() - 1);
		}
    }

    boolean isNodeWithChildern(Object node) {
    	return CommonTree.class.isInstance(node) && ((CommonTree)node).getChildren() != null;
    }
    
    void parseFallthroughNode(Object node, StringBuffer ret) {
    	//it could be xxx_COMMENT and such not explicitly caught anywhere
    	if (CommonTree.class.isInstance(node)) {
    		CommonTree tr = (CommonTree)node;
    		int type = tr.token.getType();
        	if (type == ObjcParser.IF0_COMMENT) {
        		ret.append("\n/*").append(((CommonTree)node).getChild(0).toString()).append("\n*/");
        		newLines(1,ret);
        		return;
        	} else if (type == ObjcParser.SINGLE_COMMENT || type == ObjcParser.MULTI_COMMENT) {
        		ret.append(tr.getChild(0).toString()); //this will append \n as well as a part of da comment
        		newLines(0,ret); //add desired number of \t
        		return;
        	}
    	}
    	if (node != null) {
    		if (isNodeWithChildern(node)) {
        		CommonTree tree = (CommonTree)node;
        		int i = 0;
    	        for (Object child : tree.getChildren()) {
    	        	if (isNodeWithChildern(child)) {
    		        	CommonTree tr = (CommonTree) child;
    		            parseFallthroughNode(tr,ret);
    		        } else {
    		        	if (i > 0) {
    		        		ret.append(' ');
    		        	}
    		        	processPlainNode(child,ret);
    		        }
    	        	i++;
    	        }
    		} else {
            	//ret.append(' ');
    			processPlainNode(node,ret);
    		}
    	}
    }

    void processFragment(Object node, String str, StringBuffer ret) {
    	if (!str.startsWith("@\"") && !str.startsWith("\"")) {
    		str = translateKeyword(str);
    	}
    	if (iIgnorePlainNodesBrackets && str.matches("[()]")) {
    		return;
    	}
    	if (iIgnorePlainNodesCurrledBrackets && str.matches("[{}]")) {
    		return;
    	}
    	if (iPlainNodesSlashAsSemicolon && str.equals("\\")) {
    		str = ";";
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
        parseFallthroughNode(tree,ret);
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
	            	String params = parseMethodParams(tr,false);
	            	ret.append('(').append(params).append(')');
	            	break;
	            case ObjcParser.OP:
	            	ret.append(' ').append(tr.getChild(0).toString()).append(' ');
	            	break;
	            case ObjcParser.NAME:
	            case ObjcParser.STRING:
	            case ObjcParser.BOOL:
	            case ObjcParser.ACCESS:
                	processPlainNode(tr.getChild(0),ret);
                	break;
	            case ObjcParser.NUMBER:
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
    
    String parseMethodParams(CommonTree tree,boolean bReorderParams) {
    	int type = tree.token.getType();
    	if (type != ObjcLexer.METHOD_PARAMS && type != ObjcLexer.PARAMS) {
    		return null;
    	}
    	StringBuffer ret = new StringBuffer();
    	int limit = tree.getChildCount();
    	int step = 1;
    	int start = 0;
    	if (bReorderParams) {
        	limit = -1;
        	step = -1;
        	start = tree.getChildCount() - 1;
    	}
    	while (start != limit) {
    		Object child = tree.getChild(start);
    		start += step;
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
    	boolean bSwapParams = false;
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
		                	//turn self/this in static functions to class name
		                	if (iCurrentMethodModifier.startsWith("static") && value.equals("this")) {
		                		value = iCurrentClassName;
		                	}
	                	}
	                    break;
	                case ObjcParser.METHOD_NAME:
	                	if (className != null) {
	                		//we found alloc => translate to new
	                		ret.append("new ").append(className);
	                		if (tree.getFirstChildWithType(ObjcParser.METHOD_PARAMS) == null) {
	                			ret.append("()");
	                		}
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
		                		String oname = name;
		                		translatedName = translateKeyword(name);
		                		String n = translatedName;
		                		translatedName = translateMethod(translatedName);
                				if (translatedName.equals("[") || translatedName.startsWith("+")) {
                    				ret.append(value);
	                				//preserve original name via comment
                					ret.append("/*" + oname + "*/");		                				
                    				break;
                				} else if (!translatedName.startsWith("<<")) { //"<<" => need swap value for parameter
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
		                					} else if (translatedName.startsWith(" +")) {
			                					ret.append(translatedName);
		                					} else {
		                						if (translatedName.startsWith(">>")) {
		                							bSwapParams = true;
		                							translatedName = translatedName.substring(2);
		                						}
			                					ret.append('.');
			                					name = translatedName;
					    	            		if (tree.getFirstChildWithType(ObjcParser.METHOD_PARAMS) == null) {
					    	            			//no params
					    	            			name += "()";
					    	            			n = translateMethod(name);
					    	            			ret.append(n);
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
		                			if (!translatedName.equals(oname)) {
		                				//preserve original name via comment
	                					ret.append("/*" + oname + "*/");		                				
		                			}
		                			if (translatedName.endsWith(",")) {
		                				translatedName = ",";
		                			} else {
		                				translatedName = null;
		                			}
		                		} else if (tree.getFirstChildWithType(ObjcParser.METHOD_PARAMS) == null) {
		                			//if we need to swap value and param and we don't have any param then
		                			ret.append(translatedName.substring(2)).append('(').append(value).append(')');
		                			translatedName = null;
		                		}
		                	}
	                	}
	                    break;
	                case ObjcParser.METHOD_PARAMS:
	                	String params = parseMethodParams(tr,bSwapParams);
	                	if (translatedName != null) {//=> extra dealing with params 
	                		if (translatedName.equals("[")) {
	                    		ret.append('[').append(params).append(']');
	                			break;
	                		} else if (translatedName.startsWith("+")) {
	                    		ret.append(translatedName).append(params);
	                    		break;
	                    	} else if (translatedName.equals(",")) {
	                    		ret.append(params).append(')');
	                    		break;
	                		} else {
	                			//=> need to swap value for params
	                			ret.append(params).append('.').append(translatedName.substring(2)); //skip "<<" at the start of the string
	                			params = value;
	                		}
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
	                	name = translateMethod(name);
	                	ret.append(name);
	                	break;
		            case ObjcParser.PARAMS:
		            	String params = parseMethodParams(tr,false);
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
        	params = parseMethodParams(tr,false);
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
    
    Object findFirstNodeWithType(int type, CommonTree tree) {
    	if (tree.getChildren() != null) {
	        for (Object child : tree.getChildren()) {
	        	if (CommonTree.class.isInstance(child)) {
		        	CommonTree tr = (CommonTree) child;
		        	return tr.token.getType() == type ? tr : findFirstNodeWithType(type, tr);
	        	}
	        }
    	}
        return null;
    }

    void processDefine(CommonTree tree, StringBuffer ret) {
    	tree.token.setType(ObjcParser.FIELD);
    	CommonTree value = (CommonTree)tree.getFirstChildWithType(ObjcLexer.VALUE);
    	if (value == null) {
        	value = (CommonTree)tree.getFirstChildWithType(ObjcLexer.METHOD_CALL);
        	if (value != null) {
        		iPlainNodesSlashAsSemicolon = true;
        		ret.append("//TODO CONV #define turned to function definition with typeless params");
        		newLines(1,ret);
            	String method = translateClassicalMethod(value);
            	ret.append("void ").append(method).append(" {");
        		newLines(1,ret);
                for (Object child : tree.getChildren()) {
                	if (isNodeWithChildern(child)) {
                		CommonTree tr = (CommonTree) child;
        	            switch (tr.token.getType()) {
    		            case ObjcParser.BLOCK_MULTI:
    		            case ObjcParser.BLOCK_SINGLE:
    		            	processBlock(tr,ret);
    		            	break;
    	                case ObjcParser.METHOD_CALL:
    	                	//already processed
    	                	break;
    	                default:
    	                	parseFallthroughNode(tr,ret);
    	                	break;
        	            }
    	            } else {
    	            	processPlainNode(child,ret);
    	            }
                }
                ret.append(';');
                newLines(1,ret);
                ret.append('}');
                newLines(1,ret);
                iPlainNodesSlashAsSemicolon = false;
        	}
    		return;
    	}
    	CommonTree tr = null;
    	Object node = findFirstNodeWithType(ObjcLexer.NUMBER,value);
    	if (node != null) {
    		tr = new CommonTree(new CommonToken(ObjcLexer.TYPE_PLAIN, "static int "));
    	} else {
        	node = findFirstNodeWithType(ObjcLexer.BOOL,value);    		
        	if (node != null) {
        		tr = new CommonTree(new CommonToken(ObjcLexer.TYPE_PLAIN, "static boolean "));
        	} else {
        		tr = new CommonTree(new CommonToken(ObjcLexer.TYPE_PLAIN, "static String "));
        	}
    	}
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
	                		str = translateKeyword(str);
		                	ret.append(' ').append(str).append(' ');
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
		            	ret.append("try");
		            	processBlock(tr,ret);
		            	//ret.append(' ');
		            	break;
	                case ObjcParser.CATCH_STMT:
	                	undoNewLines(ret);
	                	ret.append(" catch (");
	                	parseFallthroughNode(tr.getChild(0),ret);
	                	ret.append(' ');
	                	parseFallthroughNode(tr.getChild(1),ret);
	                	ret.append(')');
	                	Object o = tr.getChildCount() > 2 ? tr.getChild(2) : null;
	                	if (o != null && CommonTree.class.isInstance(o)) {
	                		processBlock((CommonTree)o,ret);
	                	}
	                	break;
	                case ObjcParser.FINALLY_STMT:
	                	undoNewLines(ret);
		            	ret.append(" finally");
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
    
    void processArrayInit(CommonTree tree, StringBuffer ret, boolean onTheSameLine) {
		ret.append(" {");
		if (!onTheSameLine) {
			iBlockCount++;
			newLines(1,ret);
		}
		int i = 0;
        for (Object val : tree.getChildren()) {
        	CommonTree tr = (CommonTree)val;
        	if (tr.token.getType() == ObjcParser.ARRAY_INIT) {
        		if (tr.getChildIndex() > 0) {
        			ret.append(',');
        			newLines(1,ret);
        		}
        		processArrayInit(tr,ret,true);
        	} else {
	        	String valStr = parseValue(tr);
	        	if (valStr.length() > 0) {
	        		if (i++ > 0) {
	        			ret.append(',');
	        			if (!onTheSameLine) {
	        				newLines(1,ret);
	        			}
	        		}
	        		ret.append(valStr);
	        	}
        	}
        }
		if (!onTheSameLine) {
			iBlockCount--;
			newLines(1,ret);
		}
		ret.append('}');

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
                		processArrayInit(tr,ret,false);
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
		            	String params = parseMethodParams(tr,false);
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
		            	name = translateKeyword(name);
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
    	if (type != ObjcLexer.DO_STMT) {
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
    	boolean blocks = false;
        for (Object child : tree.getChildren()) {
        	if (CommonTree.class.isInstance(child)) {
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
	                	if (blocks) {
		                	iBlockCount--;
		                	blocks = false;
	                	}
	                	newLines(1,ret);
	                	String value = ((CommonTree)tr.getChild(0)).getChild(0).toString();
	                	value = translateKeyword(value);
	                	ret.append("case ").append(value).append(':');
	                	break;
	                case ObjcParser.DEFAULT_STMT:
	                	if (blocks) {
		                	iBlockCount--;
		                	blocks = false;
	                	}
	                	newLines(1,ret);
	                	ret.append("default:");
	                	break;
	                case ObjcParser.BLOCK_MULTI:
	                	processBlock(tr,ret);
	                	break;
	                case ObjcParser.BLOCK_SINGLE:
	                	if (!blocks) {
		                	iBlockCount++;
	                		newLines(1,ret);
		                	blocks = true;
	                	}
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
    	if (blocks) {
            iBlockCount--;    		
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
    	int retMark = 0; //not used at the moment
    }
    
    static class PropertyInfo extends MethodInfo {
    	String iName; //!= null only if name is different from property => need to replace variable
    	boolean iReadOnly;
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
        boolean readOnly = false;
        for (Object child : tree.getChildren()) {
        	if (CommonTree.class.isInstance(child)) {
	            switch (((CommonTree) child).token.getType()) {
	                case ObjcParser.TYPE_PLAIN:
	                	StringBuffer b = new StringBuffer();
		            	parseTypePlain((CommonTree) child,b);
	                    type = b.toString();
	                    break;
	                case ObjcParser.FIELD_NAME:
	                    name = ((CommonTree) child).getChild(0).toString();
	                    break;
	                case ObjcParser.NAME:
	                	if (((CommonTree) child).getChild(0).toString().equals("readonly")) {
	                		readOnly = true;
	                	}
	            }
        	}
        }
        PropertyInfo info = new PropertyInfo();
        info.iReadOnly = readOnly;
        
        ret.append("//TODO CONV conversion from @property");
        newLines(1,ret);
        //add both getter & setter
        type = translateKeyword(type);
        ret.append("public ").append(type).append(" ");
        ret.append(name);
        ret.append("() {return ");
        info.iGetVarStart = ret.length();
        info.iGetVarEnd = info.iGetVarStart + name.length();
        ret.append(name).append(";}");
        if (!readOnly) {
	        newLines(1,ret);
	        ret.append("public void set").append(name).append("(").append(type).append(" a").append(name).append(") {");
	        info.iSetVarStart = ret.length();
	        info.iSetVarEnd = info.iSetVarStart + name.length();
	        ret.append(name).append(" = a").append(name).append(";}");
        }        
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
	                	ret.append("break");
	                	break;
	                case ObjcParser.CONTINUE_STMT:
	                	ret.append("continue");
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
		    	processInnerBlock(tree,ret);
		    	iBlockCount--;
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
			    			undoNewLines(ret);
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
	                case ObjcParser.ENUM:
	                	retMark = iJavaCode.length();
	                	processEnum(tr,iJavaCode,null);
	                	break;
	            	case ObjcParser.STRUCT:
	            	case ObjcParser.UNION:
	                	retMark = iJavaCode.length();
	            		processStructUnion(tr,iJavaCode,null);
	            		break;
	                case ObjcParser.TYPE_PLAIN:
	                	retMark = iJavaCode.length();
	                	parseTypePlain(tr, iJavaCode);
	                	break;
	                case ObjcParser.METHOD_NAME:
	                	if (retMark == 0) {
	                		//panic!!:)
	                		break;
	                	}
	            		name = tr.getChild(0).toString();
	            		String tname = keywordTranslation.get(name);
	            		if (tname != null) {
	            			int i = tname.indexOf('\n');
	            			if (i > -1) {
	            				String s = tname.substring(0, i);
	            				String fr = iJavaCode.substring(retMark);
	            				iJavaCode.delete(retMark,iJavaCode.length());
		            			iJavaCode.append(s);
	            				newLines(1,iJavaCode);
	            				retMark = iJavaCode.length();
	            				iJavaCode.append(fr);
		            			name = tname.substring(i + 1);
	            			} else {
	            				name = tname;
	            			}
	            		}
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
            			iCurrentMethodModifier = modifier;
            			iJavaCode.append(" ").append(name);
	            		if (tree.getFirstChildWithType(ObjcParser.METHOD_PARAMS) == null) {
	            			//no params
	            			iJavaCode.append("()");
	            		}
	                	break;
	                case ObjcParser.METHOD_PARAMS:
	                	String params = parseMethodParams(tr,false);
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
    
    void addIMPLEMENTS_INTERFACESandFinishHeader(CommonTree tree, StringBuffer ret, String keyWord) {
		if (tree != null) {
			String implementsIntf = null;
	        for (Object o : tree.getChildren()) {
	        	CommonTree intr = (CommonTree)o;
	        	if (implementsIntf != null) {
	        		ret.append(',');
	        	} else {
	        		ret.append(keyWord);		
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
		                	name = translateKeyword(name);
		                	iJavaCode.append("extends ").append(name).append(' ');
	                		ctr =(CommonTree) tr.getFirstChildWithType(ObjcParser.IMPLEMENTS_INTERFACES);
	                	}
	                	addIMPLEMENTS_INTERFACESandFinishHeader(ctr,iJavaCode,"implements ");
	                	break;
	                case ObjcParser.GROUP_MODIFIER:
	                	scope = tr.getChild(0).toString();
	                	scope = translateKeyword(scope) + ' ';
	                	break;
	                case ObjcParser.TYPEDEF:
	                	processTypedef(tr,iJavaCode);
	                	break;
	                case ObjcParser.VARIABLE:
	                case ObjcParser.FIELD:
	                	if (scope != null) {
	                		iJavaCode.append(scope);
	                	}
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
	                	methodName = translateKeyword(methodName);
	                	//if we've got a property of the same name already in it we will not replace it
	                	//TODO this isn't ideal as it makes all method of the same name public signature regardless but what the hell....
	                	if (info.iMethods.get(methodName) == null) {
	                		info.iMethods.put(methodName, new MethodInfo()); 
	                	}
	                	break;
	                default:
	    	        	iIgnorePlainNodesCurrledBrackets = true;
	                	parseFallthroughNode(tr,iJavaCode);
	    	        	iIgnorePlainNodesCurrledBrackets = false;
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
                			addIMPLEMENTS_INTERFACESandFinishHeader(null,iJavaCode,"extends ");
                		}
	                	break;
	                case ObjcParser.IMPLEMENTS_INTERFACES: //always present
	                	addIMPLEMENTS_INTERFACESandFinishHeader(tr,iJavaCode,"extends ");
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
						if (!pi.iReadOnly) {
							iJavaCode.replace(pi.iSetVarStart + diff, pi.iSetVarEnd + diff, pi.iName);
						}
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
        	//try to find a class of the same name as the processed file
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
    	iJavaCodeGlobal.append("public static class ").append("Global {");
    	iBlockCount++;
    	newLines(1,iJavaCodeGlobal);
    	iGlobalScope = fileName;
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
