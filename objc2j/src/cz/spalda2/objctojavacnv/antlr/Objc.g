grammar Objc;

options {
  output = AST;
  k = 4;
}

tokens {
  ACCESS;
  ARRAY_VALUE;
  ARRAY_INIT;
  BLOCK_SINGLE;
  BLOCK_MULTI;
  BLOCK_CALL;
  BOOL;
  BREAK_STMT;
  CASE_STMT;
  CATCH_STMT;
  CODE;
  CONTINUE_STMT;
	DEFAULT_STMT;
	DEFINE;
	DIRECTIVE;
  DO_STMT;
  ELSE_STMT;
  EXPR_COMPLEMENT;
  EXPR_NOT;
  EXPR_NEGATE;
  FINALLY_STMT;
  FOR_IN_STMT;
  FOR_STMT;
  GOTO;
  IF_STMT;
  IMPLEMENTATION;
  METHOD_CALL;
  METHOD_IMPL;
  METHOD_MSG;
  MULTI_COMMENT;
  NUMBER;
  OBJECT_ACCESS;
  OP;
  PARAMS;
  PREFIX;
  QUESTION_MARK_IF;
  RETURN_STMT;
  SIMPLE_EXPR;
  SINGLE_COMMENT;
  STATIC;
  STATIC_VARIABLE;
  STRING;
  SYNCHRONIZED;
  SYNTHETIZE;
  SWITCH_STMT;
  THROW_STMT;
  TRY_STMT;
  TYPE_CAST;
  VA_ARGS;
	VALUE;
  VARIABLE;
  VARIABLE_ASSIGNMENT;
	WHILE_STMT;

// .h tokens
	TYPEDEF;
	TYPEDEF_NAME;
	INTERFACE_NAME;
	SUPERCLASS_NAME;
	FIELDS;
	FIELD;
	FORWARD_DECLARATION;
	TYPE_PLAIN;
	FIELD_NAME;
	IMPLEMENTS_INTERFACE_NAME;
	IMPLEMENTS_INTERFACES;
	INTERFACE;
	METHOD;
	METHOD_NAME;
	METHOD_PARAMS;
	METHOD_PARAM;
  PROPERTY;
	PARAM_NAME;
	PARAM_PREFIX;
	PROTOCOL_NAME;
	TEMPLATE_TYPE;
	STATIC_METHOD;
	STRUCT_DEC;
	EXTERN;
	FUNC_POINTER;
	MODIFIER;
	GROUP_MODIFIER;
	PROTOCOL;
	NAME;
	CATEGORY;
	CLASS_NAME;
	ENUM;
	ENUM_DEC;
	ENUM_FIELD;
	STRUCT;
	VALUE;
	UNION;
	UNION_DEC;
}

@header {
package cz.spalda2.objctojavacnv.antlr.output;
}
@lexer::header {
package cz.spalda2.objctojavacnv.antlr.output;
}


/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
code	:	
		code_internal+ -> ^(CODE code_internal+)
	;
	
code_internal
	:	implementation_wrapper
	| forward_class_declaration
  | protocol_declaration_wrapper 
  | interface_declaration_wrapper 
  | extern_declaration_wrapper
  | define_declaration
  | typedef_declaration_wrapper
  | static_declaration_wrapper
  | variable_declaration_wrapper
  | comments
	;

comments
  : single_comment
  | multi_comment
  ;
  
single_comment
  : SINGLE_COMMENT_LIT ->^(SINGLE_COMMENT SINGLE_COMMENT_LIT)
  ;
  
multi_comment
  : MULTI_COMMENT_LIT -> ^(MULTI_COMMENT MULTI_COMMENT_LIT)
  ;
  
/*------------------------------------------------------------------
 * IMPLEMENTATION RULES
 *------------------------------------------------------------------*/
 
 extern_wrapper
 	:	extern -> ^(EXTERN)
 	;
 
 extern	:	'extern'  '"C"'  '(' ~(')') ')'
 	;
 	
implementation_wrapper
 	:	implementation -> ^(IMPLEMENTATION implementation);
 	
implementation
	:	'@implementation'  name  category?
		implementation_body+
		'@end'  ';'?
	; 	

implementation_body
  : implementation_method_wrapper
  | directives
  | define_declaration
	| typedef_declaration_wrapper
	| static_declaration_wrapper
  | variable_declaration_wrapper
  | comments
	| ';'
	;

directives
	:	synthesize -> ^(SYNTHETIZE synthesize)
	;	
	
synthesize
	:	('@synthesize' | '@dynamic')  ID ('=' name)? ';' -> ^(PROPERTY ID name?)
	;	
	
static_declaration_wrapper
	: 'static' field_declaration -> ^(STATIC_VARIABLE field_declaration)
	;

block_call_predicate
  : '^('
  ;
  
block_call_wrapper
  : block_call -> ^(BLOCK_CALL block_call)
  ;

block_call
  : ('^(' block_params? ')') block_multiline_wrapper
  ;

synchronized_call_wrapper
  : synchronized_call -> ^(SYNCHRONIZED synchronized_call)
  ;
  
synchronized_call
  : '@synchronized' '(' element_value ')' block_multiline_wrapper
  ;
  
block_params
  : (classical_param (',' classical_param)*) -> ^(PARAMS classical_param+)
  ; 

  	
block
	:	block_multiline ->^(BLOCK_MULTI block_multiline)
	|	block_singleline ->^(BLOCK_SINGLE block_singleline)
	;

block_multiline
	:		
		'{'
			block_internal*		
		'}'
	;

block_multiline_wrapper
  : block_multiline -> ^(BLOCK_MULTI block_multiline)
  ;

block_singleline_wrapper
  : block_singleline -> ^(BLOCK_SINGLE block_singleline)
  ;
  
block_singleline
	: synchronized_call_wrapper
	| do_stmt
	|	if_stmt
	| else_stmt
	|	switch_stmt
	|	return_stmt
	|	for_stmt_wrapper
	|	while_stmt_wrapper
	|	';'
  | break_stmt
  | continue_stmt
	|	goto_stmt
	| method_msg
  | variable_assignment_wrapper
  | comments
	;

block_internal
  : (clasical_method_call_predicate)=> classical_method_call_wrapper
  | synchronized_call_wrapper
  | try_stmt_wrapper
	|	throw_stmt_wrapper
	|	static_declaration_wrapper
	|	do_stmt
	|	if_stmt
	| else_stmt
	|	switch_stmt
	|	return_stmt
	|	for_stmt_wrapper
	|	while_stmt_wrapper
	|	';'
	| break_stmt
	| continue_stmt
	|	goto_stmt
	| block_multiline_wrapper
  | variable_declaration_wrapper
  | method_msg
  | variable_assignment_wrapper
  | comments
	;

goto_stmt
  : 'goto' ':' ID ';' -> ^(GOTO ID)
  ;
  
while_stmt_wrapper
  : while_stmt -> ^(WHILE_STMT while_stmt)
  ;
  
while_stmt
  : 'while' '(' element_value ')' block
  ;
  
for_in_stmt
  : 'in' element_value -> ^(FOR_IN_STMT element_value)
  ;
  
for_classic_stmt
  : ';' element_value? ';' element_value?
  ;
  
for_stmt_body
  : (name ';')=> name for_classic_stmt ')' block
  | (name 'in')=> name for_in_stmt ')' block
  | field_declaration_simple ((op_assign_wrapper element_value for_classic_stmt) | for_in_stmt) ')' block
  | variable_assignment_wrapper for_classic_stmt ')' block
  ;

for_stmt
  : 'for' '(' for_stmt_body
  ;

for_stmt_wrapper
  : for_stmt -> ^(FOR_STMT for_stmt)
  ;
  
return_stmt
  : 'return' element_value_or_semicolon -> ^(RETURN_STMT element_value_or_semicolon)
  ;
  
switch_stmt
  : 'switch' '(' element_value ')' '{' (switch_body*) '}' -> ^(SWITCH_STMT element_value switch_body*)
  ;
  
switch_body
  : case_stmt
  | default_stmt
  | block_multiline_wrapper
  | block_singleline_wrapper
  ;
  
case_stmt
  : 'case' name_number ':' -> ^(CASE_STMT name_number)
  ;
  
default_stmt
  : 'default' ':' -> ^(DEFAULT_STMT)
  ;
  
do_stmt
  : 'do' block 'while' '(' element_value ')' -> ^(DO_STMT block element_value)
  ;
  
if_stmt
  : 'if' '(' element_value ')' block -> ^(IF_STMT element_value block)
  ;

else_stmt
  : 'else' block -> ^(ELSE_STMT block)
  ;
  
try_stmt_wrapper
	:	'@try'  block  catch_stmt* finally_stmt? -> ^(TRY_STMT block catch_stmt* finally_stmt?)
	;

catch_stmt
	:	'@catch'  '('  name  '*' name  ')'  block -> ^(CATCH_STMT name name block)
	;	

finally_stmt
	:	'@finally'  block -> ^(FINALLY_STMT block)
	;

throw_stmt_wrapper
	:	throw_stmt -> ^(THROW_STMT throw_stmt)
	;

throw_stmt
	:	'@throw' element_value ';'
	;

name
	: ID -> ^(NAME ID)
	;

name_number
  : ID -> ^(NAME ID)
  | NUMBER_LITERAL -> ^(NUMBER NUMBER_LITERAL)
  ;
  
variable_declaration_wrapper
  : field_declaration_wrapper -> ^(VARIABLE field_declaration_wrapper)
  ;
  
variable_assignment_wrapper
  : variable_assignment -> ^(VARIABLE_ASSIGNMENT variable_assignment)
  ;
  
variable_assignment
  : object_access_wrapper op_assign_wrapper element_value
  ;

method_implementation
  : method_modifier_wrapper method_implementation_common;
  
method_implementation_common
  : ('(' type_declaration ')' method_name) method_params?;  

implementation_method_wrapper
  :method_implementation
  block_multiline_wrapper -> ^(METHOD_IMPL method_implementation block_multiline_wrapper)
  ;

classical_method_call_wrapper
  : classical_method_call -> ^(METHOD_CALL classical_method_call)
  ;

clasical_method_call_predicate
  : name (access_wrapper name)* '('
  ;
  
classical_method_call
  : name (access_wrapper name)* ('(' classical_method_params_push? ')')
  ;

classical_method_params_push
  : (method_param_push (',' method_param_push)*) -> ^(PARAMS method_param_push+)
  ;

method_params_push
	:	method_param_push (((prefix ':') | ',') method_param_push)* -> ^(METHOD_PARAMS method_param_push+);

method_param_push
  : element_value -> ^(METHOD_PARAM element_value)
  ; 

method_msg
  : '[' element_value method_name (':' method_params_push)? ']' ->^(METHOD_MSG element_value method_name method_params_push?)
  ;

questionmark_if_stmt
  : ('?' element_value ':' element_value) -> ^(QUESTION_MARK_IF element_value element_value)
  ;
  
element_value
	:	simple_expression -> ^(VALUE simple_expression)
	;	
	
element_value_or_semicolon
  : (';' | element_value)
  ;
  
element_value_or_array_init
  : '{' element_value (',' element_value)* '}' -> ^(ARRAY_INIT element_value+)
  | element_value
  ;
  
increment_decrement
  : ('++' | '--')
  ;
  
simple_expression
	: cast_unary_expression1 op2_unary_expression_or_questionmark_if_stmt*
	; 
	
cast_unary_expression1
  : '-' cast_expression -> ^(EXPR_NEGATE cast_expression)
  | cast_unary_expression
  ;

op2_unary_expression_or_questionmark_if_stmt
  : (op2_wrapper cast_unary_expression2)
  | questionmark_if_stmt
  ;

cast_unary_expression
  : cast_expression increment_decrement?
  | '!' cast_expression -> ^(EXPR_NOT cast_expression)
  | '~' cast_expression -> ^(EXPR_COMPLEMENT cast_expression)
  | increment_decrement cast_expression
  ;

cast_unary_expression2
  : cast_unary_expression
//  | (op2_wrapper cast_unary_expression2)
  ;
  
cast_expression
  : (type_cast_wrapper)=> type_cast_wrapper simple_expression_value_access
  | ('(' type_cast_wrapper)=> '(' type_cast_wrapper simple_expression_value_access ')' (access_wrapper name)+
  | simple_expression_value_access
  ;

simple_expression_value_access
  : (simple_expression_value (access_wrapper name)* '(')=> simple_expression_value (access_wrapper name)* ('(' classical_method_params_push? ')')
  | simple_expression_value2 (access_wrapper name)*
  ;
  
simple_expression_value
	:	STRING_LITERAL -> ^(STRING STRING_LITERAL)
	| STRING_OBJC -> ^(STRING STRING_OBJC)
	|	NUMBER_LITERAL -> ^(NUMBER NUMBER_LITERAL)
	| BOOL_LITERAL -> ^(BOOL BOOL_LITERAL)
  | '&'? name
  | known_directives -> ^(DIRECTIVE known_directives)
	;

simple_expression_value2
  : (name '[')=> name '[' element_value ']' -> ^(ARRAY_VALUE name element_value)
  | block_call_wrapper
  | simple_expression_value
  | method_msg
  | '(' element_value ')'
  ;
	
op2_wrapper
  : op2 -> ^(OP op2)
  ;
  
op2	:	('|' | '&' | '<<' | '>>' | '+' | '-' | '*' | '&&' | '||' | '<' | '>' | '<=' | '>=' | '==' | '!=' | '^' | '=');

op_assign_wrapper
  : op_assign -> ^(OP op_assign)
  ;

op_assign
  : ('|=' | '&=' | '+=' | '-=' | '*=' | '=' | '<<=' | '>>=')
  ;
  
known_directives
  : '@selector'
  ;
   
type_cast_wrapper
	:	type_cast_name -> ^(TYPE_CAST type_cast_name)
	;

type_cast_name
  //: ID '*'+ -> ^(TYPE_PLAIN ID)
  : '(' type_cast_declaration ')'
  ;

type_cast_declaration
  : (type_decl_protocol_predicate)=> type_declaration_protocol
  | classical_type_declaration
  ; 

access
	: '.' | '->'
	;

access_wrapper
	:	access ->^(ACCESS access)
	;

object_access_wrapper
	:	object_access -> ^(OBJECT_ACCESS object_access)
	;

object_access
	:	name ')'? (access_wrapper name ')'? )*
	;
	
break_stmt
  : 'break' ';' -> ^(BREAK_STMT)
  ;
  
continue_stmt
  : 'continue' ';' ->^(CONTINUE_STMT)
  ;
  
/*-------------------------------------------------------------------
* HEADER FILE RULES
*--------------------------------------------------------------------*/    
extern_declaration_wrapper
  : extern_declaration -> ^(EXTERN extern_declaration); 
  
extern_declaration
  : 'extern' field_declaration;
  
declarations
  : optional_prefix? method_declaration -> ^(METHOD method_declaration)
  | property_prefix optional_prefix? field_declaration -> ^(PROPERTY field_declaration)
  | comments
  ;
  
field_declaration_wrapper
  : field_declaration -> ^(FIELD field_declaration);
  
  
optional_prefix
  : '@optional';
  
ns_inline
  : 'NS_INLINE';  

property_prefix
  : '@property' '(' name ('=' name)? (',' name ('=' name)?)* ')'; 

/* This stupid ANTLR gets confused when parsing [obj retain] since it's got 'retain' as a keyword here huh...
hence the above with a name only 
property_prefix_param
  : 'readwrite'
  | 'readonly'
  | 'retain'
  | 'nonatomic';  
*/
interface_declaration_wrapper
  : interface_declaration -> ^(INTERFACE interface_declaration);
  
protocol_declaration_wrapper
  : protocol_declaration -> ^(PROTOCOL protocol_declaration);

typedef_declaration_wrapper
  : 'typedef' typedef_declaration -> ^(TYPEDEF typedef_declaration);

typedef_declaration
  : typedef_internal typedef_name (',' typedef_name)* ';';
  
func_pointer_predicate
  : '(' '*' type_declaration_plane ')'
  ;
  
func_pointer
  : '(' '*' type_declaration_plane ')' '(' (type_declaration_plane (',' type_declaration_plane)*)? ')' -> ^(FUNC_POINTER type_declaration_plane+)
  ;
  
typedef_internal
  : (func_pointer_predicate)=> func_pointer
  | type_declaration_struct
  | type_declaration_enum
  | type_declaration_union
  | type_declaration_plane
  ;
  
typedef_name
  : ID -> ^(TYPEDEF_NAME ID);

forward_class_declaration
  : '@class' class_name ';' ->^(FORWARD_DECLARATION class_name)
  ; 

class_name
  : ID -> ^(CLASS_NAME ID);
  
protocol_declaration
  : '@protocol' protocol_name implements_interfaces?
     class_protocol_end
  ; 

class_protocol_end
  : class_protocol_end1
  | class_protocol_end2;

class_protocol_end1
  : declarations*
    '@end'
  ;
  
class_protocol_end2
  : ';'
  ;
  
template: '<' template_internal '>';

implements_interfaces
  :implements_protocols -> ^(IMPLEMENTS_INTERFACES implements_protocols)
  ;
  
implements_protocols  
  : '<' type_dec_internal (',' type_dec_internal)* '>' -> ^(IMPLEMENTS_INTERFACE_NAME type_dec_internal)+
  ;

template_internal
  : template_type (',' template_type)*;
  
template_type
  : template_name
  | 'struct' template_name
  ;
  
template_name
  : ID -> ^(TEMPLATE_TYPE ID)
  ;
  
protocol_name
  : ID -> ^(PROTOCOL_NAME ID);    
  
interface_declaration
  : '@interface' interface_name superclass_name
  fields_body?
  declarations*
  '@end'
  ;
  
fields_body
  :
  '{'
    body_item*
  '}'
  ;
  
body_item
  : group_modifier_wrapper? (typedef_declaration_wrapper | field_declaration_wrapper)
  | comments
  ;

group_modifier_wrapper
  : group_modifier -> ^(GROUP_MODIFIER group_modifier); 
  
group_modifier
  : '@private' | '@protected' | '@public';  
    
interface_name
  : ID category? -> ^(INTERFACE_NAME ID category?);

category: '(' ID? ')' -> ^(CATEGORY ID?);

superclass_name
  : (':' ID)? implements_interfaces? -> ^(SUPERCLASS_NAME ID? implements_interfaces?)
  ; 
  
fields_declarations
  : field_declaration+ -> ^(FIELD field_declaration)+;
  
field_declaration
  : ((type_declaration field_name) (',' field_name)* (((classical_method_params (';' | block_multiline_wrapper)) | (op_assign_wrapper element_value_or_array_init ';')) | ';'))
  ;

field_declaration_simple
  : type_declaration field_name -> ^(FIELD type_declaration field_name)
  ;
  
classical_method_params
  : '(' classical_param? (',' classical_param)* ')' -> ^(PARAMS classical_param*)
  ; 
  
classical_param
  : classical_type_declaration param_name ->^(METHOD_PARAM classical_type_declaration param_name)
  ;
  
classical_type_declaration
  : (type_decl_protocol_predicate)=> type_declaration_protocol
  | (func_pointer_predicate)=> func_pointer
  | type_declaration_plane '&'?
  | ('struct' type_dec_wrapper '&'?) -> ^(STRUCT type_dec_wrapper)
  | ('union' type_dec_wrapper '&'?) -> ^(UNION type_dec_wrapper)
  | ('enum' type_dec_wrapper '&'?) -> ^(ENUM type_dec_wrapper)
  ; 

type_declaration
  : (type_decl_protocol_predicate)=> type_declaration_protocol
  | (func_pointer_predicate)=> func_pointer
  | type_declaration_struct '&'?
  | type_declaration_enum '&'?
  | type_declaration_union '&'?
  | type_declaration_plane '&'?
  ; 

type_declaration_plane
  : 'const'? 'unsigned'? type_dec 'const'? -> ^(TYPE_PLAIN type_dec)
  ; 

type_decl_protocol_predicate
  : ('id' '<')
  ;
  
type_declaration_protocol
  : 'id' '<' type_dec_internal '>' -> ^(TYPE_PLAIN type_dec_internal)
  ; 

type_declaration_struct
  : ('struct' typedef_name? '{')=> 'struct' typedef_name? struct_wrapper ->^(STRUCT_DEC typedef_name? struct_wrapper)
  | 'struct' typedef_name ->^(STRUCT typedef_name)
  ; 

type_declaration_union
  : ('union' typedef_name? '{')=> 'union' typedef_name? struct_wrapper ->^(UNION_DEC typedef_name? struct_wrapper)
  |  'union' typedef_name ->^(UNION typedef_name)
  ; 

type_declaration_enum
  : ('enum' typedef_name? '{')=> 'enum' typedef_name? enum_wrapper ->^(ENUM_DEC typedef_name? enum_wrapper)
  | 'enum' typedef_name ->^(ENUM typedef_name)
  ;

enum_wrapper
  : '{'
        enum_element+
    '}'
  ;
  
enum_element
  : name (op_assign_wrapper element_value)? ','? ->^(ENUM_FIELD name (op_assign_wrapper element_value)?)
  | comments
  ;
  
struct_wrapper
  : struct -> ^(STRUCT struct);

struct
  : '{' 
      struct_internal+
    '}'
  ; 

struct_internal
  : field_declaration_wrapper
  | comments
  ;
  
type_dec_wrapper
  : type_dec -> ^(TYPE_PLAIN type_dec)
  ;
  
type_dec: type_dec_internal '*'* ('['  NUMBER_LITERAL? ']')*
  ;
  
type_dec_internal
  : knownTypes 
  |   ID
  ;

knownTypes
  : 'int'
  | 'id'
  | 'long'+ 'int'?
  | 'short'+ 'int'?
  ;
  
field_name
  : ID array_size* (field_crap)? -> ^(FIELD_NAME ID array_size*);     

array_size
  : '[' NUMBER_LITERAL? ']';

field_crap
  : ':' NUMBER_LITERAL;
  
method_declaration
  : method_modifier_wrapper method_declaration_variants;
  
method_modifier_wrapper
  : method_modifier -> ^(MODIFIER method_modifier);
  
method_modifier
  : '+' | '-';
  
method_declaration_variants
  :  method_declaration_common;  

method_declaration_common
  : ('(' type_declaration ')' method_name) method_params? ';';  

method_declaration_predicate
  : method_modifier ('(' type_declaration ')' method_name)
  ;
    
method_name
  : ID -> ^(METHOD_NAME ID)
  ;
  
method_params
  : method_param+ -> ^(METHOD_PARAMS method_param+);

method_param
  : prefix? ':' ('(' type_declaration ')')? param_name -> ^(METHOD_PARAM prefix? ':' '(' type_declaration ')' param_name);  
  
param_name
  : ID -> ^(PARAM_NAME ID);
  
prefix  : ID -> ^(PARAM_PREFIX ID);

//works for 1 line defines only
define_declaration
  :  DEFINE_LITERAL name element_value -> ^(DEFINE name element_value)
  ;
  
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

SINGLE_COMMENT_LIT
  : '//' ~('\r' | '\n')* ('\r'? '\n')+;

MULTI_COMMENT_LIT options { greedy = false; }
  : '/*' .* '*/' ('\r'? '\n')?;

/* 
SINGLE_COMMENT
	:	'//' ~('\r' | '\n')* ('\r'? '\n')+ { skip(); };
*/

//MULTI_COMMENT options { greedy = false; }
// 	: '/*' .* '*/' ('\r'? '\n')? { skip(); };

IF0_COMMENT options { greedy = false; }
  : '#if 0' .* ('#else' | '#endif') ('\r'? '\n')? { skip(); };

DEFINE_LITERAL
  : '#define';
  
PREPROCESSOR_DECLARATION
	: '#' ~('d') ~('\r' | '\n')* ('\r' | '\n')+ { skip(); }
	; 	

NUMBER_LITERAL  : '-'? DIGIT+ 
	| '0x' ('A'|'B'|'C'|'D'|'E'|'F'|DIGIT)+;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+  { $channel = HIDDEN; } ;

STRING_LITERAL
      :   '"' ( EscapeSequence | (options {greedy=false;} : ~('\u0000'..'\u001f' | '\\' | '"' ) ) )* '"'
      ;

fragment EscapeSequence 
  : '\\'
    ( 
      'n' 
  | 'r' 
  | 't'
  | '\'' 
  | '\\'
  | UnicodeEscape
  )
  ;

fragment UnicodeEscape
      :     'u' HexDigit HexDigit HexDigit HexDigit 
      ;

fragment HexDigit 
  :   ('0'..'9'|'a'..'f'|'A'..'F');

STRING_OBJC
	: '@' STRING_LITERAL;

BOOL_LITERAL
  : ('YES' | 'NO' | 'true' | 'false');
  
ID
  : LETTER (DIGIT|LETTER)*;

fragment DIGIT  : '0'..'9' ;
fragment LETTER	: ('a'..'z' | 'A'..'Z' | '_');  
  