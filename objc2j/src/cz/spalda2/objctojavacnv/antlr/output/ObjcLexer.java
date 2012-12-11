// $ANTLR 3.4 /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g 2012-12-09 16:16:22

package cz.spalda2.objctojavacnv.antlr.output;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class ObjcLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__110=110;
    public static final int T__111=111;
    public static final int T__112=112;
    public static final int T__113=113;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__120=120;
    public static final int T__121=121;
    public static final int T__122=122;
    public static final int T__123=123;
    public static final int T__124=124;
    public static final int T__125=125;
    public static final int T__126=126;
    public static final int T__127=127;
    public static final int T__128=128;
    public static final int T__129=129;
    public static final int T__130=130;
    public static final int T__131=131;
    public static final int T__132=132;
    public static final int T__133=133;
    public static final int T__134=134;
    public static final int T__135=135;
    public static final int T__136=136;
    public static final int T__137=137;
    public static final int T__138=138;
    public static final int T__139=139;
    public static final int T__140=140;
    public static final int T__141=141;
    public static final int T__142=142;
    public static final int T__143=143;
    public static final int T__144=144;
    public static final int T__145=145;
    public static final int T__146=146;
    public static final int T__147=147;
    public static final int T__148=148;
    public static final int T__149=149;
    public static final int T__150=150;
    public static final int T__151=151;
    public static final int T__152=152;
    public static final int T__153=153;
    public static final int T__154=154;
    public static final int T__155=155;
    public static final int T__156=156;
    public static final int T__157=157;
    public static final int T__158=158;
    public static final int T__159=159;
    public static final int T__160=160;
    public static final int T__161=161;
    public static final int T__162=162;
    public static final int T__163=163;
    public static final int T__164=164;
    public static final int T__165=165;
    public static final int T__166=166;
    public static final int T__167=167;
    public static final int T__168=168;
    public static final int T__169=169;
    public static final int T__170=170;
    public static final int T__171=171;
    public static final int T__172=172;
    public static final int T__173=173;
    public static final int T__174=174;
    public static final int T__175=175;
    public static final int T__176=176;
    public static final int T__177=177;
    public static final int T__178=178;
    public static final int T__179=179;
    public static final int T__180=180;
    public static final int T__181=181;
    public static final int T__182=182;
    public static final int T__183=183;
    public static final int T__184=184;
    public static final int T__185=185;
    public static final int T__186=186;
    public static final int T__187=187;
    public static final int T__188=188;
    public static final int T__189=189;
    public static final int T__190=190;
    public static final int T__191=191;
    public static final int T__192=192;
    public static final int T__193=193;
    public static final int T__194=194;
    public static final int T__195=195;
    public static final int ACCESS=4;
    public static final int ARRAY_INIT=5;
    public static final int ARRAY_VALUE=6;
    public static final int BLOCK_CALL=7;
    public static final int BLOCK_MULTI=8;
    public static final int BLOCK_SINGLE=9;
    public static final int BOOL=10;
    public static final int BOOL_LITERAL=11;
    public static final int BREAK_STMT=12;
    public static final int CASE_STMT=13;
    public static final int CATCH_STMT=14;
    public static final int CATEGORY=15;
    public static final int CLASS_NAME=16;
    public static final int CODE=17;
    public static final int CONTINUE_STMT=18;
    public static final int DEFAULT_STMT=19;
    public static final int DEFINE=20;
    public static final int DEFINE_LITERAL=21;
    public static final int DIGIT=22;
    public static final int DIRECTIVE=23;
    public static final int DO_STMT=24;
    public static final int ELSE_STMT=25;
    public static final int ENUM=26;
    public static final int ENUM_DEC=27;
    public static final int ENUM_FIELD=28;
    public static final int EXPR_COMPLEMENT=29;
    public static final int EXPR_NEGATE=30;
    public static final int EXPR_NOT=31;
    public static final int EXTERN=32;
    public static final int EscapeSequence=33;
    public static final int FIELD=34;
    public static final int FIELDS=35;
    public static final int FIELD_NAME=36;
    public static final int FINALLY_STMT=37;
    public static final int FORWARD_DECLARATION=38;
    public static final int FOR_IN_STMT=39;
    public static final int FOR_STMT=40;
    public static final int FUNC_POINTER=41;
    public static final int GOTO=42;
    public static final int GROUP_MODIFIER=43;
    public static final int HexDigit=44;
    public static final int ID=45;
    public static final int IF0_COMMENT=46;
    public static final int IF_STMT=47;
    public static final int IMPLEMENTATION=48;
    public static final int IMPLEMENTS_INTERFACES=49;
    public static final int IMPLEMENTS_INTERFACE_NAME=50;
    public static final int INTERFACE=51;
    public static final int INTERFACE_NAME=52;
    public static final int LETTER=53;
    public static final int METHOD=54;
    public static final int METHOD_CALL=55;
    public static final int METHOD_IMPL=56;
    public static final int METHOD_MSG=57;
    public static final int METHOD_NAME=58;
    public static final int METHOD_PARAM=59;
    public static final int METHOD_PARAMS=60;
    public static final int MODIFIER=61;
    public static final int MULTI_COMMENT=62;
    public static final int MULTI_COMMENT_LIT=63;
    public static final int NAME=64;
    public static final int NUMBER=65;
    public static final int NUMBER_LITERAL=66;
    public static final int OBJECT_ACCESS=67;
    public static final int OP=68;
    public static final int PARAMS=69;
    public static final int PARAM_NAME=70;
    public static final int PARAM_PREFIX=71;
    public static final int PREFIX=72;
    public static final int PREPROCESSOR_DECLARATION=73;
    public static final int PROPERTY=74;
    public static final int PROTOCOL=75;
    public static final int PROTOCOL_NAME=76;
    public static final int QUESTION_MARK_IF=77;
    public static final int RETURN_STMT=78;
    public static final int SIMPLE_EXPR=79;
    public static final int SINGLE_COMMENT=80;
    public static final int SINGLE_COMMENT_LIT=81;
    public static final int STATIC=82;
    public static final int STATIC_METHOD=83;
    public static final int STATIC_VARIABLE=84;
    public static final int STRING=85;
    public static final int STRING_LITERAL=86;
    public static final int STRING_OBJC=87;
    public static final int STRUCT=88;
    public static final int STRUCT_DEC=89;
    public static final int SUPERCLASS_NAME=90;
    public static final int SWITCH_STMT=91;
    public static final int SYNCHRONIZED=92;
    public static final int SYNTHETIZE=93;
    public static final int TEMPLATE_TYPE=94;
    public static final int THROW_STMT=95;
    public static final int TRY_STMT=96;
    public static final int TYPEDEF=97;
    public static final int TYPEDEF_NAME=98;
    public static final int TYPE_CAST=99;
    public static final int TYPE_PLAIN=100;
    public static final int UNION=101;
    public static final int UNION_DEC=102;
    public static final int UnicodeEscape=103;
    public static final int VALUE=104;
    public static final int VARIABLE=105;
    public static final int VARIABLE_ASSIGNMENT=106;
    public static final int VA_ARGS=107;
    public static final int WHILE_STMT=108;
    public static final int WHITESPACE=109;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public ObjcLexer() {} 
    public ObjcLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ObjcLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g"; }

    // $ANTLR start "T__110"
    public final void mT__110() throws RecognitionException {
        try {
            int _type = T__110;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:6:8: ( '!' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:6:10: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__110"

    // $ANTLR start "T__111"
    public final void mT__111() throws RecognitionException {
        try {
            int _type = T__111;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:7:8: ( '!=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:7:10: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__111"

    // $ANTLR start "T__112"
    public final void mT__112() throws RecognitionException {
        try {
            int _type = T__112;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:8:8: ( '\"C\"' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:8:10: '\"C\"'
            {
            match("\"C\""); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__112"

    // $ANTLR start "T__113"
    public final void mT__113() throws RecognitionException {
        try {
            int _type = T__113;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:9:8: ( '&&' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:9:10: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__113"

    // $ANTLR start "T__114"
    public final void mT__114() throws RecognitionException {
        try {
            int _type = T__114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:10:8: ( '&' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:10:10: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__114"

    // $ANTLR start "T__115"
    public final void mT__115() throws RecognitionException {
        try {
            int _type = T__115;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:11:8: ( '&=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:11:10: '&='
            {
            match("&="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__115"

    // $ANTLR start "T__116"
    public final void mT__116() throws RecognitionException {
        try {
            int _type = T__116;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:12:8: ( '(' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:12:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__116"

    // $ANTLR start "T__117"
    public final void mT__117() throws RecognitionException {
        try {
            int _type = T__117;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:13:8: ( ')' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:13:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__117"

    // $ANTLR start "T__118"
    public final void mT__118() throws RecognitionException {
        try {
            int _type = T__118;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:14:8: ( '*' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:14:10: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__118"

    // $ANTLR start "T__119"
    public final void mT__119() throws RecognitionException {
        try {
            int _type = T__119;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:15:8: ( '*=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:15:10: '*='
            {
            match("*="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__119"

    // $ANTLR start "T__120"
    public final void mT__120() throws RecognitionException {
        try {
            int _type = T__120;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:16:8: ( '+' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:16:10: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__120"

    // $ANTLR start "T__121"
    public final void mT__121() throws RecognitionException {
        try {
            int _type = T__121;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:17:8: ( '++' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:17:10: '++'
            {
            match("++"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__121"

    // $ANTLR start "T__122"
    public final void mT__122() throws RecognitionException {
        try {
            int _type = T__122;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:18:8: ( '+=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:18:10: '+='
            {
            match("+="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__122"

    // $ANTLR start "T__123"
    public final void mT__123() throws RecognitionException {
        try {
            int _type = T__123;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:19:8: ( ',' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:19:10: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__123"

    // $ANTLR start "T__124"
    public final void mT__124() throws RecognitionException {
        try {
            int _type = T__124;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:20:8: ( '-' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:20:10: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__124"

    // $ANTLR start "T__125"
    public final void mT__125() throws RecognitionException {
        try {
            int _type = T__125;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:21:8: ( '--' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:21:10: '--'
            {
            match("--"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__125"

    // $ANTLR start "T__126"
    public final void mT__126() throws RecognitionException {
        try {
            int _type = T__126;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:22:8: ( '-=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:22:10: '-='
            {
            match("-="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__126"

    // $ANTLR start "T__127"
    public final void mT__127() throws RecognitionException {
        try {
            int _type = T__127;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:23:8: ( '->' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:23:10: '->'
            {
            match("->"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__127"

    // $ANTLR start "T__128"
    public final void mT__128() throws RecognitionException {
        try {
            int _type = T__128;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:24:8: ( '.' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:24:10: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__128"

    // $ANTLR start "T__129"
    public final void mT__129() throws RecognitionException {
        try {
            int _type = T__129;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:25:8: ( ':' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:25:10: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__129"

    // $ANTLR start "T__130"
    public final void mT__130() throws RecognitionException {
        try {
            int _type = T__130;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:26:8: ( ';' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:26:10: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__130"

    // $ANTLR start "T__131"
    public final void mT__131() throws RecognitionException {
        try {
            int _type = T__131;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:27:8: ( '<' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:27:10: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__131"

    // $ANTLR start "T__132"
    public final void mT__132() throws RecognitionException {
        try {
            int _type = T__132;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:28:8: ( '<<' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:28:10: '<<'
            {
            match("<<"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__132"

    // $ANTLR start "T__133"
    public final void mT__133() throws RecognitionException {
        try {
            int _type = T__133;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:29:8: ( '<<=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:29:10: '<<='
            {
            match("<<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__133"

    // $ANTLR start "T__134"
    public final void mT__134() throws RecognitionException {
        try {
            int _type = T__134;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:30:8: ( '<=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:30:10: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__134"

    // $ANTLR start "T__135"
    public final void mT__135() throws RecognitionException {
        try {
            int _type = T__135;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:31:8: ( '=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:31:10: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__135"

    // $ANTLR start "T__136"
    public final void mT__136() throws RecognitionException {
        try {
            int _type = T__136;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:32:8: ( '==' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:32:10: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__136"

    // $ANTLR start "T__137"
    public final void mT__137() throws RecognitionException {
        try {
            int _type = T__137;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:33:8: ( '>' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:33:10: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__137"

    // $ANTLR start "T__138"
    public final void mT__138() throws RecognitionException {
        try {
            int _type = T__138;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:34:8: ( '>=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:34:10: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__138"

    // $ANTLR start "T__139"
    public final void mT__139() throws RecognitionException {
        try {
            int _type = T__139;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:35:8: ( '>>' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:35:10: '>>'
            {
            match(">>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__139"

    // $ANTLR start "T__140"
    public final void mT__140() throws RecognitionException {
        try {
            int _type = T__140;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:36:8: ( '>>=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:36:10: '>>='
            {
            match(">>="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__140"

    // $ANTLR start "T__141"
    public final void mT__141() throws RecognitionException {
        try {
            int _type = T__141;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:37:8: ( '?' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:37:10: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__141"

    // $ANTLR start "T__142"
    public final void mT__142() throws RecognitionException {
        try {
            int _type = T__142;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:38:8: ( '@catch' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:38:10: '@catch'
            {
            match("@catch"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__142"

    // $ANTLR start "T__143"
    public final void mT__143() throws RecognitionException {
        try {
            int _type = T__143;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:39:8: ( '@class' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:39:10: '@class'
            {
            match("@class"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__143"

    // $ANTLR start "T__144"
    public final void mT__144() throws RecognitionException {
        try {
            int _type = T__144;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:40:8: ( '@dynamic' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:40:10: '@dynamic'
            {
            match("@dynamic"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__144"

    // $ANTLR start "T__145"
    public final void mT__145() throws RecognitionException {
        try {
            int _type = T__145;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:41:8: ( '@end' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:41:10: '@end'
            {
            match("@end"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__145"

    // $ANTLR start "T__146"
    public final void mT__146() throws RecognitionException {
        try {
            int _type = T__146;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:42:8: ( '@finally' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:42:10: '@finally'
            {
            match("@finally"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__146"

    // $ANTLR start "T__147"
    public final void mT__147() throws RecognitionException {
        try {
            int _type = T__147;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:43:8: ( '@implementation' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:43:10: '@implementation'
            {
            match("@implementation"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__147"

    // $ANTLR start "T__148"
    public final void mT__148() throws RecognitionException {
        try {
            int _type = T__148;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:44:8: ( '@interface' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:44:10: '@interface'
            {
            match("@interface"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__148"

    // $ANTLR start "T__149"
    public final void mT__149() throws RecognitionException {
        try {
            int _type = T__149;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:45:8: ( '@optional' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:45:10: '@optional'
            {
            match("@optional"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__149"

    // $ANTLR start "T__150"
    public final void mT__150() throws RecognitionException {
        try {
            int _type = T__150;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:46:8: ( '@private' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:46:10: '@private'
            {
            match("@private"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__150"

    // $ANTLR start "T__151"
    public final void mT__151() throws RecognitionException {
        try {
            int _type = T__151;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:47:8: ( '@property' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:47:10: '@property'
            {
            match("@property"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__151"

    // $ANTLR start "T__152"
    public final void mT__152() throws RecognitionException {
        try {
            int _type = T__152;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:48:8: ( '@protected' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:48:10: '@protected'
            {
            match("@protected"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__152"

    // $ANTLR start "T__153"
    public final void mT__153() throws RecognitionException {
        try {
            int _type = T__153;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:49:8: ( '@protocol' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:49:10: '@protocol'
            {
            match("@protocol"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__153"

    // $ANTLR start "T__154"
    public final void mT__154() throws RecognitionException {
        try {
            int _type = T__154;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:50:8: ( '@public' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:50:10: '@public'
            {
            match("@public"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__154"

    // $ANTLR start "T__155"
    public final void mT__155() throws RecognitionException {
        try {
            int _type = T__155;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:51:8: ( '@selector' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:51:10: '@selector'
            {
            match("@selector"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__155"

    // $ANTLR start "T__156"
    public final void mT__156() throws RecognitionException {
        try {
            int _type = T__156;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:52:8: ( '@synchronized' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:52:10: '@synchronized'
            {
            match("@synchronized"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__156"

    // $ANTLR start "T__157"
    public final void mT__157() throws RecognitionException {
        try {
            int _type = T__157;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:53:8: ( '@synthesize' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:53:10: '@synthesize'
            {
            match("@synthesize"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__157"

    // $ANTLR start "T__158"
    public final void mT__158() throws RecognitionException {
        try {
            int _type = T__158;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:54:8: ( '@throw' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:54:10: '@throw'
            {
            match("@throw"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__158"

    // $ANTLR start "T__159"
    public final void mT__159() throws RecognitionException {
        try {
            int _type = T__159;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:55:8: ( '@try' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:55:10: '@try'
            {
            match("@try"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__159"

    // $ANTLR start "T__160"
    public final void mT__160() throws RecognitionException {
        try {
            int _type = T__160;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:56:8: ( 'NS_INLINE' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:56:10: 'NS_INLINE'
            {
            match("NS_INLINE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__160"

    // $ANTLR start "T__161"
    public final void mT__161() throws RecognitionException {
        try {
            int _type = T__161;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:57:8: ( '[' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:57:10: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__161"

    // $ANTLR start "T__162"
    public final void mT__162() throws RecognitionException {
        try {
            int _type = T__162;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:58:8: ( ']' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:58:10: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__162"

    // $ANTLR start "T__163"
    public final void mT__163() throws RecognitionException {
        try {
            int _type = T__163;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:59:8: ( '^' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:59:10: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__163"

    // $ANTLR start "T__164"
    public final void mT__164() throws RecognitionException {
        try {
            int _type = T__164;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:60:8: ( '^(' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:60:10: '^('
            {
            match("^("); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__164"

    // $ANTLR start "T__165"
    public final void mT__165() throws RecognitionException {
        try {
            int _type = T__165;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:61:8: ( 'break' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:61:10: 'break'
            {
            match("break"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__165"

    // $ANTLR start "T__166"
    public final void mT__166() throws RecognitionException {
        try {
            int _type = T__166;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:62:8: ( 'case' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:62:10: 'case'
            {
            match("case"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__166"

    // $ANTLR start "T__167"
    public final void mT__167() throws RecognitionException {
        try {
            int _type = T__167;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:63:8: ( 'const' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:63:10: 'const'
            {
            match("const"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__167"

    // $ANTLR start "T__168"
    public final void mT__168() throws RecognitionException {
        try {
            int _type = T__168;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:64:8: ( 'continue' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:64:10: 'continue'
            {
            match("continue"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__168"

    // $ANTLR start "T__169"
    public final void mT__169() throws RecognitionException {
        try {
            int _type = T__169;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:65:8: ( 'default' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:65:10: 'default'
            {
            match("default"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__169"

    // $ANTLR start "T__170"
    public final void mT__170() throws RecognitionException {
        try {
            int _type = T__170;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:66:8: ( 'do' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:66:10: 'do'
            {
            match("do"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__170"

    // $ANTLR start "T__171"
    public final void mT__171() throws RecognitionException {
        try {
            int _type = T__171;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:67:8: ( 'else' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:67:10: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__171"

    // $ANTLR start "T__172"
    public final void mT__172() throws RecognitionException {
        try {
            int _type = T__172;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:68:8: ( 'enum' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:68:10: 'enum'
            {
            match("enum"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__172"

    // $ANTLR start "T__173"
    public final void mT__173() throws RecognitionException {
        try {
            int _type = T__173;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:69:8: ( 'extern' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:69:10: 'extern'
            {
            match("extern"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__173"

    // $ANTLR start "T__174"
    public final void mT__174() throws RecognitionException {
        try {
            int _type = T__174;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:70:8: ( 'for' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:70:10: 'for'
            {
            match("for"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__174"

    // $ANTLR start "T__175"
    public final void mT__175() throws RecognitionException {
        try {
            int _type = T__175;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:71:8: ( 'goto' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:71:10: 'goto'
            {
            match("goto"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__175"

    // $ANTLR start "T__176"
    public final void mT__176() throws RecognitionException {
        try {
            int _type = T__176;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:72:8: ( 'id' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:72:10: 'id'
            {
            match("id"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__176"

    // $ANTLR start "T__177"
    public final void mT__177() throws RecognitionException {
        try {
            int _type = T__177;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:73:8: ( 'if' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:73:10: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__177"

    // $ANTLR start "T__178"
    public final void mT__178() throws RecognitionException {
        try {
            int _type = T__178;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:74:8: ( 'in' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:74:10: 'in'
            {
            match("in"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__178"

    // $ANTLR start "T__179"
    public final void mT__179() throws RecognitionException {
        try {
            int _type = T__179;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:75:8: ( 'int' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:75:10: 'int'
            {
            match("int"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__179"

    // $ANTLR start "T__180"
    public final void mT__180() throws RecognitionException {
        try {
            int _type = T__180;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:76:8: ( 'long' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:76:10: 'long'
            {
            match("long"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__180"

    // $ANTLR start "T__181"
    public final void mT__181() throws RecognitionException {
        try {
            int _type = T__181;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:77:8: ( 'return' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:77:10: 'return'
            {
            match("return"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__181"

    // $ANTLR start "T__182"
    public final void mT__182() throws RecognitionException {
        try {
            int _type = T__182;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:78:8: ( 'short' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:78:10: 'short'
            {
            match("short"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__182"

    // $ANTLR start "T__183"
    public final void mT__183() throws RecognitionException {
        try {
            int _type = T__183;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:79:8: ( 'static' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:79:10: 'static'
            {
            match("static"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__183"

    // $ANTLR start "T__184"
    public final void mT__184() throws RecognitionException {
        try {
            int _type = T__184;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:80:8: ( 'struct' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:80:10: 'struct'
            {
            match("struct"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__184"

    // $ANTLR start "T__185"
    public final void mT__185() throws RecognitionException {
        try {
            int _type = T__185;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:81:8: ( 'switch' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:81:10: 'switch'
            {
            match("switch"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__185"

    // $ANTLR start "T__186"
    public final void mT__186() throws RecognitionException {
        try {
            int _type = T__186;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:82:8: ( 'typedef' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:82:10: 'typedef'
            {
            match("typedef"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__186"

    // $ANTLR start "T__187"
    public final void mT__187() throws RecognitionException {
        try {
            int _type = T__187;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:83:8: ( 'union' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:83:10: 'union'
            {
            match("union"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__187"

    // $ANTLR start "T__188"
    public final void mT__188() throws RecognitionException {
        try {
            int _type = T__188;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:84:8: ( 'unsigned' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:84:10: 'unsigned'
            {
            match("unsigned"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__188"

    // $ANTLR start "T__189"
    public final void mT__189() throws RecognitionException {
        try {
            int _type = T__189;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:85:8: ( 'while' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:85:10: 'while'
            {
            match("while"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__189"

    // $ANTLR start "T__190"
    public final void mT__190() throws RecognitionException {
        try {
            int _type = T__190;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:86:8: ( '{' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:86:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__190"

    // $ANTLR start "T__191"
    public final void mT__191() throws RecognitionException {
        try {
            int _type = T__191;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:87:8: ( '|' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:87:10: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__191"

    // $ANTLR start "T__192"
    public final void mT__192() throws RecognitionException {
        try {
            int _type = T__192;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:88:8: ( '|=' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:88:10: '|='
            {
            match("|="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__192"

    // $ANTLR start "T__193"
    public final void mT__193() throws RecognitionException {
        try {
            int _type = T__193;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:89:8: ( '||' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:89:10: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__193"

    // $ANTLR start "T__194"
    public final void mT__194() throws RecognitionException {
        try {
            int _type = T__194;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:90:8: ( '}' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:90:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__194"

    // $ANTLR start "T__195"
    public final void mT__195() throws RecognitionException {
        try {
            int _type = T__195;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:91:8: ( '~' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:91:10: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__195"

    // $ANTLR start "SINGLE_COMMENT_LIT"
    public final void mSINGLE_COMMENT_LIT() throws RecognitionException {
        try {
            int _type = SINGLE_COMMENT_LIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:867:3: ( '//' (~ ( '\\r' | '\\n' ) )* ( ( '\\r' )? '\\n' )+ )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:867:5: '//' (~ ( '\\r' | '\\n' ) )* ( ( '\\r' )? '\\n' )+
            {
            match("//"); 



            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:867:10: (~ ( '\\r' | '\\n' ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '\u0000' && LA1_0 <= '\t')||(LA1_0 >= '\u000B' && LA1_0 <= '\f')||(LA1_0 >= '\u000E' && LA1_0 <= '\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:867:26: ( ( '\\r' )? '\\n' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\n'||LA3_0=='\r') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:867:27: ( '\\r' )? '\\n'
            	    {
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:867:27: ( '\\r' )?
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0=='\r') ) {
            	        alt2=1;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:867:27: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }


            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SINGLE_COMMENT_LIT"

    // $ANTLR start "MULTI_COMMENT_LIT"
    public final void mMULTI_COMMENT_LIT() throws RecognitionException {
        try {
            int _type = MULTI_COMMENT_LIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:870:3: ( '/*' ( . )* '*/' ( ( '\\r' )? '\\n' )? )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:870:5: '/*' ( . )* '*/' ( ( '\\r' )? '\\n' )?
            {
            match("/*"); 



            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:870:10: ( . )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='*') ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1=='/') ) {
                        alt4=2;
                    }
                    else if ( ((LA4_1 >= '\u0000' && LA4_1 <= '.')||(LA4_1 >= '0' && LA4_1 <= '\uFFFF')) ) {
                        alt4=1;
                    }


                }
                else if ( ((LA4_0 >= '\u0000' && LA4_0 <= ')')||(LA4_0 >= '+' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:870:10: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            match("*/"); 



            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:870:18: ( ( '\\r' )? '\\n' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\n'||LA6_0=='\r') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:870:19: ( '\\r' )? '\\n'
                    {
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:870:19: ( '\\r' )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='\r') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:870:19: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }


                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MULTI_COMMENT_LIT"

    // $ANTLR start "IF0_COMMENT"
    public final void mIF0_COMMENT() throws RecognitionException {
        try {
            int _type = IF0_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:3: ( '#if 0' ( . )* ( '#else' | '#endif' ) ( ( '\\r' )? '\\n' )? )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:5: '#if 0' ( . )* ( '#else' | '#endif' ) ( ( '\\r' )? '\\n' )?
            {
            match("#if 0"); 



            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:13: ( . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='#') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='e') ) {
                        int LA7_3 = input.LA(3);

                        if ( (LA7_3=='l') ) {
                            int LA7_4 = input.LA(4);

                            if ( (LA7_4=='s') ) {
                                int LA7_6 = input.LA(5);

                                if ( (LA7_6=='e') ) {
                                    alt7=2;
                                }
                                else if ( ((LA7_6 >= '\u0000' && LA7_6 <= 'd')||(LA7_6 >= 'f' && LA7_6 <= '\uFFFF')) ) {
                                    alt7=1;
                                }


                            }
                            else if ( ((LA7_4 >= '\u0000' && LA7_4 <= 'r')||(LA7_4 >= 't' && LA7_4 <= '\uFFFF')) ) {
                                alt7=1;
                            }


                        }
                        else if ( (LA7_3=='n') ) {
                            int LA7_5 = input.LA(4);

                            if ( (LA7_5=='d') ) {
                                int LA7_7 = input.LA(5);

                                if ( (LA7_7=='i') ) {
                                    int LA7_9 = input.LA(6);

                                    if ( (LA7_9=='f') ) {
                                        alt7=2;
                                    }
                                    else if ( ((LA7_9 >= '\u0000' && LA7_9 <= 'e')||(LA7_9 >= 'g' && LA7_9 <= '\uFFFF')) ) {
                                        alt7=1;
                                    }


                                }
                                else if ( ((LA7_7 >= '\u0000' && LA7_7 <= 'h')||(LA7_7 >= 'j' && LA7_7 <= '\uFFFF')) ) {
                                    alt7=1;
                                }


                            }
                            else if ( ((LA7_5 >= '\u0000' && LA7_5 <= 'c')||(LA7_5 >= 'e' && LA7_5 <= '\uFFFF')) ) {
                                alt7=1;
                            }


                        }
                        else if ( ((LA7_3 >= '\u0000' && LA7_3 <= 'k')||LA7_3=='m'||(LA7_3 >= 'o' && LA7_3 <= '\uFFFF')) ) {
                            alt7=1;
                        }


                    }
                    else if ( ((LA7_1 >= '\u0000' && LA7_1 <= 'd')||(LA7_1 >= 'f' && LA7_1 <= '\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0 >= '\u0000' && LA7_0 <= '\"')||(LA7_0 >= '$' && LA7_0 <= '\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:13: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:16: ( '#else' | '#endif' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='#') ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1=='e') ) {
                    int LA8_2 = input.LA(3);

                    if ( (LA8_2=='l') ) {
                        alt8=1;
                    }
                    else if ( (LA8_2=='n') ) {
                        alt8=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:17: '#else'
                    {
                    match("#else"); 



                    }
                    break;
                case 2 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:27: '#endif'
                    {
                    match("#endif"); 



                    }
                    break;

            }


            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:37: ( ( '\\r' )? '\\n' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:38: ( '\\r' )? '\\n'
                    {
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:38: ( '\\r' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:881:38: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }


                    match('\n'); 

                    }
                    break;

            }


             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IF0_COMMENT"

    // $ANTLR start "DEFINE_LITERAL"
    public final void mDEFINE_LITERAL() throws RecognitionException {
        try {
            int _type = DEFINE_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:884:3: ( '#define' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:884:5: '#define'
            {
            match("#define"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEFINE_LITERAL"

    // $ANTLR start "PREPROCESSOR_DECLARATION"
    public final void mPREPROCESSOR_DECLARATION() throws RecognitionException {
        try {
            int _type = PREPROCESSOR_DECLARATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:887:2: ( '#' ~ ( 'd' ) (~ ( '\\r' | '\\n' ) )* ( '\\r' | '\\n' )+ )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:887:4: '#' ~ ( 'd' ) (~ ( '\\r' | '\\n' ) )* ( '\\r' | '\\n' )+
            {
            match('#'); 

            if ( (input.LA(1) >= '\u0000' && input.LA(1) <= 'c')||(input.LA(1) >= 'e' && input.LA(1) <= '\uFFFF') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:887:15: (~ ( '\\r' | '\\n' ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0 >= '\u0000' && LA11_0 <= '\t')||(LA11_0 >= '\u000B' && LA11_0 <= '\f')||(LA11_0 >= '\u000E' && LA11_0 <= '\uFFFF')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:887:31: ( '\\r' | '\\n' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='\n'||LA12_0=='\r') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
            	    {
            	    if ( input.LA(1)=='\n'||input.LA(1)=='\r' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PREPROCESSOR_DECLARATION"

    // $ANTLR start "NUMBER_LITERAL"
    public final void mNUMBER_LITERAL() throws RecognitionException {
        try {
            int _type = NUMBER_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:890:17: ( ( '-' )? ( DIGIT )+ | '0x' ( 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | DIGIT )+ )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='-'||(LA16_0 >= '1' && LA16_0 <= '9')) ) {
                alt16=1;
            }
            else if ( (LA16_0=='0') ) {
                int LA16_2 = input.LA(2);

                if ( (LA16_2=='x') ) {
                    alt16=2;
                }
                else {
                    alt16=1;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:890:19: ( '-' )? ( DIGIT )+
                    {
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:890:19: ( '-' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='-') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:890:19: '-'
                            {
                            match('-'); 

                            }
                            break;

                    }


                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:890:24: ( DIGIT )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0 >= '0' && LA14_0 <= '9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:891:4: '0x' ( 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | DIGIT )+
                    {
                    match("0x"); 



                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:891:9: ( 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | DIGIT )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0 >= '0' && LA15_0 <= '9')||(LA15_0 >= 'A' && LA15_0 <= 'F')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER_LITERAL"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:893:12: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:893:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:893:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0 >= '\t' && LA17_0 <= '\n')||(LA17_0 >= '\f' && LA17_0 <= '\r')||LA17_0==' ') ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);


             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:896:7: ( '\"' ( EscapeSequence | ( options {greedy=false; } :~ ( '\\u0000' .. '\\u001f' | '\\\\' | '\"' ) ) )* '\"' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:896:11: '\"' ( EscapeSequence | ( options {greedy=false; } :~ ( '\\u0000' .. '\\u001f' | '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 

            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:896:15: ( EscapeSequence | ( options {greedy=false; } :~ ( '\\u0000' .. '\\u001f' | '\\\\' | '\"' ) ) )*
            loop18:
            do {
                int alt18=3;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='\\') ) {
                    alt18=1;
                }
                else if ( ((LA18_0 >= ' ' && LA18_0 <= '!')||(LA18_0 >= '#' && LA18_0 <= '[')||(LA18_0 >= ']' && LA18_0 <= '\uFFFF')) ) {
                    alt18=2;
                }


                switch (alt18) {
            	case 1 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:896:17: EscapeSequence
            	    {
            	    mEscapeSequence(); 


            	    }
            	    break;
            	case 2 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:896:34: ( options {greedy=false; } :~ ( '\\u0000' .. '\\u001f' | '\\\\' | '\"' ) )
            	    {
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:896:34: ( options {greedy=false; } :~ ( '\\u0000' .. '\\u001f' | '\\\\' | '\"' ) )
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:896:61: ~ ( '\\u0000' .. '\\u001f' | '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1) >= ' ' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "EscapeSequence"
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:900:3: ( '\\\\' ( 'n' | 'r' | 't' | '\\'' | '\\\\' | UnicodeEscape ) )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:900:5: '\\\\' ( 'n' | 'r' | 't' | '\\'' | '\\\\' | UnicodeEscape )
            {
            match('\\'); 

            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:901:5: ( 'n' | 'r' | 't' | '\\'' | '\\\\' | UnicodeEscape )
            int alt19=6;
            switch ( input.LA(1) ) {
            case 'n':
                {
                alt19=1;
                }
                break;
            case 'r':
                {
                alt19=2;
                }
                break;
            case 't':
                {
                alt19=3;
                }
                break;
            case '\'':
                {
                alt19=4;
                }
                break;
            case '\\':
                {
                alt19=5;
                }
                break;
            case 'u':
                {
                alt19=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:902:7: 'n'
                    {
                    match('n'); 

                    }
                    break;
                case 2 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:903:5: 'r'
                    {
                    match('r'); 

                    }
                    break;
                case 3 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:904:5: 't'
                    {
                    match('t'); 

                    }
                    break;
                case 4 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:905:5: '\\''
                    {
                    match('\''); 

                    }
                    break;
                case 5 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:906:5: '\\\\'
                    {
                    match('\\'); 

                    }
                    break;
                case 6 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:907:5: UnicodeEscape
                    {
                    mUnicodeEscape(); 


                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EscapeSequence"

    // $ANTLR start "UnicodeEscape"
    public final void mUnicodeEscape() throws RecognitionException {
        try {
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:912:7: ( 'u' HexDigit HexDigit HexDigit HexDigit )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:912:13: 'u' HexDigit HexDigit HexDigit HexDigit
            {
            match('u'); 

            mHexDigit(); 


            mHexDigit(); 


            mHexDigit(); 


            mHexDigit(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UnicodeEscape"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:916:3: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "STRING_OBJC"
    public final void mSTRING_OBJC() throws RecognitionException {
        try {
            int _type = STRING_OBJC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:919:2: ( '@' STRING_LITERAL )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:919:4: '@' STRING_LITERAL
            {
            match('@'); 

            mSTRING_LITERAL(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING_OBJC"

    // $ANTLR start "BOOL_LITERAL"
    public final void mBOOL_LITERAL() throws RecognitionException {
        try {
            int _type = BOOL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:922:3: ( ( 'YES' | 'NO' | 'true' | 'false' ) )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:922:5: ( 'YES' | 'NO' | 'true' | 'false' )
            {
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:922:5: ( 'YES' | 'NO' | 'true' | 'false' )
            int alt20=4;
            switch ( input.LA(1) ) {
            case 'Y':
                {
                alt20=1;
                }
                break;
            case 'N':
                {
                alt20=2;
                }
                break;
            case 't':
                {
                alt20=3;
                }
                break;
            case 'f':
                {
                alt20=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }

            switch (alt20) {
                case 1 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:922:6: 'YES'
                    {
                    match("YES"); 



                    }
                    break;
                case 2 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:922:14: 'NO'
                    {
                    match("NO"); 



                    }
                    break;
                case 3 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:922:21: 'true'
                    {
                    match("true"); 



                    }
                    break;
                case 4 :
                    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:922:30: 'false'
                    {
                    match("false"); 



                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOL_LITERAL"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:925:3: ( LETTER ( DIGIT | LETTER )* )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:925:5: LETTER ( DIGIT | LETTER )*
            {
            mLETTER(); 


            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:925:12: ( DIGIT | LETTER )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0 >= '0' && LA21_0 <= '9')||(LA21_0 >= 'A' && LA21_0 <= 'Z')||LA21_0=='_'||(LA21_0 >= 'a' && LA21_0 <= 'z')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:927:17: ( '0' .. '9' )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:928:17: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) )
            // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    public void mTokens() throws RecognitionException {
        // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:8: ( T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | T__130 | T__131 | T__132 | T__133 | T__134 | T__135 | T__136 | T__137 | T__138 | T__139 | T__140 | T__141 | T__142 | T__143 | T__144 | T__145 | T__146 | T__147 | T__148 | T__149 | T__150 | T__151 | T__152 | T__153 | T__154 | T__155 | T__156 | T__157 | T__158 | T__159 | T__160 | T__161 | T__162 | T__163 | T__164 | T__165 | T__166 | T__167 | T__168 | T__169 | T__170 | T__171 | T__172 | T__173 | T__174 | T__175 | T__176 | T__177 | T__178 | T__179 | T__180 | T__181 | T__182 | T__183 | T__184 | T__185 | T__186 | T__187 | T__188 | T__189 | T__190 | T__191 | T__192 | T__193 | T__194 | T__195 | SINGLE_COMMENT_LIT | MULTI_COMMENT_LIT | IF0_COMMENT | DEFINE_LITERAL | PREPROCESSOR_DECLARATION | NUMBER_LITERAL | WHITESPACE | STRING_LITERAL | STRING_OBJC | BOOL_LITERAL | ID )
        int alt22=97;
        alt22 = dfa22.predict(input);
        switch (alt22) {
            case 1 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:10: T__110
                {
                mT__110(); 


                }
                break;
            case 2 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:17: T__111
                {
                mT__111(); 


                }
                break;
            case 3 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:24: T__112
                {
                mT__112(); 


                }
                break;
            case 4 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:31: T__113
                {
                mT__113(); 


                }
                break;
            case 5 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:38: T__114
                {
                mT__114(); 


                }
                break;
            case 6 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:45: T__115
                {
                mT__115(); 


                }
                break;
            case 7 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:52: T__116
                {
                mT__116(); 


                }
                break;
            case 8 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:59: T__117
                {
                mT__117(); 


                }
                break;
            case 9 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:66: T__118
                {
                mT__118(); 


                }
                break;
            case 10 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:73: T__119
                {
                mT__119(); 


                }
                break;
            case 11 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:80: T__120
                {
                mT__120(); 


                }
                break;
            case 12 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:87: T__121
                {
                mT__121(); 


                }
                break;
            case 13 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:94: T__122
                {
                mT__122(); 


                }
                break;
            case 14 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:101: T__123
                {
                mT__123(); 


                }
                break;
            case 15 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:108: T__124
                {
                mT__124(); 


                }
                break;
            case 16 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:115: T__125
                {
                mT__125(); 


                }
                break;
            case 17 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:122: T__126
                {
                mT__126(); 


                }
                break;
            case 18 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:129: T__127
                {
                mT__127(); 


                }
                break;
            case 19 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:136: T__128
                {
                mT__128(); 


                }
                break;
            case 20 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:143: T__129
                {
                mT__129(); 


                }
                break;
            case 21 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:150: T__130
                {
                mT__130(); 


                }
                break;
            case 22 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:157: T__131
                {
                mT__131(); 


                }
                break;
            case 23 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:164: T__132
                {
                mT__132(); 


                }
                break;
            case 24 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:171: T__133
                {
                mT__133(); 


                }
                break;
            case 25 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:178: T__134
                {
                mT__134(); 


                }
                break;
            case 26 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:185: T__135
                {
                mT__135(); 


                }
                break;
            case 27 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:192: T__136
                {
                mT__136(); 


                }
                break;
            case 28 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:199: T__137
                {
                mT__137(); 


                }
                break;
            case 29 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:206: T__138
                {
                mT__138(); 


                }
                break;
            case 30 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:213: T__139
                {
                mT__139(); 


                }
                break;
            case 31 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:220: T__140
                {
                mT__140(); 


                }
                break;
            case 32 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:227: T__141
                {
                mT__141(); 


                }
                break;
            case 33 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:234: T__142
                {
                mT__142(); 


                }
                break;
            case 34 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:241: T__143
                {
                mT__143(); 


                }
                break;
            case 35 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:248: T__144
                {
                mT__144(); 


                }
                break;
            case 36 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:255: T__145
                {
                mT__145(); 


                }
                break;
            case 37 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:262: T__146
                {
                mT__146(); 


                }
                break;
            case 38 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:269: T__147
                {
                mT__147(); 


                }
                break;
            case 39 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:276: T__148
                {
                mT__148(); 


                }
                break;
            case 40 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:283: T__149
                {
                mT__149(); 


                }
                break;
            case 41 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:290: T__150
                {
                mT__150(); 


                }
                break;
            case 42 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:297: T__151
                {
                mT__151(); 


                }
                break;
            case 43 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:304: T__152
                {
                mT__152(); 


                }
                break;
            case 44 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:311: T__153
                {
                mT__153(); 


                }
                break;
            case 45 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:318: T__154
                {
                mT__154(); 


                }
                break;
            case 46 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:325: T__155
                {
                mT__155(); 


                }
                break;
            case 47 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:332: T__156
                {
                mT__156(); 


                }
                break;
            case 48 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:339: T__157
                {
                mT__157(); 


                }
                break;
            case 49 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:346: T__158
                {
                mT__158(); 


                }
                break;
            case 50 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:353: T__159
                {
                mT__159(); 


                }
                break;
            case 51 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:360: T__160
                {
                mT__160(); 


                }
                break;
            case 52 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:367: T__161
                {
                mT__161(); 


                }
                break;
            case 53 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:374: T__162
                {
                mT__162(); 


                }
                break;
            case 54 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:381: T__163
                {
                mT__163(); 


                }
                break;
            case 55 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:388: T__164
                {
                mT__164(); 


                }
                break;
            case 56 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:395: T__165
                {
                mT__165(); 


                }
                break;
            case 57 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:402: T__166
                {
                mT__166(); 


                }
                break;
            case 58 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:409: T__167
                {
                mT__167(); 


                }
                break;
            case 59 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:416: T__168
                {
                mT__168(); 


                }
                break;
            case 60 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:423: T__169
                {
                mT__169(); 


                }
                break;
            case 61 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:430: T__170
                {
                mT__170(); 


                }
                break;
            case 62 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:437: T__171
                {
                mT__171(); 


                }
                break;
            case 63 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:444: T__172
                {
                mT__172(); 


                }
                break;
            case 64 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:451: T__173
                {
                mT__173(); 


                }
                break;
            case 65 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:458: T__174
                {
                mT__174(); 


                }
                break;
            case 66 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:465: T__175
                {
                mT__175(); 


                }
                break;
            case 67 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:472: T__176
                {
                mT__176(); 


                }
                break;
            case 68 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:479: T__177
                {
                mT__177(); 


                }
                break;
            case 69 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:486: T__178
                {
                mT__178(); 


                }
                break;
            case 70 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:493: T__179
                {
                mT__179(); 


                }
                break;
            case 71 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:500: T__180
                {
                mT__180(); 


                }
                break;
            case 72 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:507: T__181
                {
                mT__181(); 


                }
                break;
            case 73 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:514: T__182
                {
                mT__182(); 


                }
                break;
            case 74 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:521: T__183
                {
                mT__183(); 


                }
                break;
            case 75 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:528: T__184
                {
                mT__184(); 


                }
                break;
            case 76 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:535: T__185
                {
                mT__185(); 


                }
                break;
            case 77 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:542: T__186
                {
                mT__186(); 


                }
                break;
            case 78 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:549: T__187
                {
                mT__187(); 


                }
                break;
            case 79 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:556: T__188
                {
                mT__188(); 


                }
                break;
            case 80 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:563: T__189
                {
                mT__189(); 


                }
                break;
            case 81 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:570: T__190
                {
                mT__190(); 


                }
                break;
            case 82 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:577: T__191
                {
                mT__191(); 


                }
                break;
            case 83 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:584: T__192
                {
                mT__192(); 


                }
                break;
            case 84 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:591: T__193
                {
                mT__193(); 


                }
                break;
            case 85 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:598: T__194
                {
                mT__194(); 


                }
                break;
            case 86 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:605: T__195
                {
                mT__195(); 


                }
                break;
            case 87 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:612: SINGLE_COMMENT_LIT
                {
                mSINGLE_COMMENT_LIT(); 


                }
                break;
            case 88 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:631: MULTI_COMMENT_LIT
                {
                mMULTI_COMMENT_LIT(); 


                }
                break;
            case 89 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:649: IF0_COMMENT
                {
                mIF0_COMMENT(); 


                }
                break;
            case 90 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:661: DEFINE_LITERAL
                {
                mDEFINE_LITERAL(); 


                }
                break;
            case 91 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:676: PREPROCESSOR_DECLARATION
                {
                mPREPROCESSOR_DECLARATION(); 


                }
                break;
            case 92 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:701: NUMBER_LITERAL
                {
                mNUMBER_LITERAL(); 


                }
                break;
            case 93 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:716: WHITESPACE
                {
                mWHITESPACE(); 


                }
                break;
            case 94 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:727: STRING_LITERAL
                {
                mSTRING_LITERAL(); 


                }
                break;
            case 95 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:742: STRING_OBJC
                {
                mSTRING_OBJC(); 


                }
                break;
            case 96 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:754: BOOL_LITERAL
                {
                mBOOL_LITERAL(); 


                }
                break;
            case 97 :
                // /Users/spalda2/workspace/objc2java/objc2j/src/cz/spalda2/objctojavacnv/antlr/Objc.g:1:767: ID
                {
                mID(); 


                }
                break;

        }

    }


    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA22_eotS =
        "\1\uffff\1\56\1\uffff\1\63\2\uffff\1\65\1\70\1\uffff\1\74\3\uffff"+
        "\1\77\1\101\1\104\2\uffff\1\54\2\uffff\1\122\15\54\1\uffff\1\154"+
        "\6\uffff\1\54\21\uffff\1\165\5\uffff\1\167\13\uffff\1\54\1\u0083"+
        "\2\uffff\4\54\1\u0088\6\54\1\u008f\1\u0090\1\u0092\11\54\10\uffff"+
        "\1\54\17\uffff\1\54\1\uffff\4\54\1\uffff\3\54\1\u00ad\2\54\2\uffff"+
        "\1\u00b0\1\uffff\13\54\1\uffff\1\u0083\4\uffff\2\54\1\u00c3\3\54"+
        "\1\u00c7\1\u00c8\1\54\1\uffff\1\54\1\u00cb\1\uffff\1\u00cc\6\54"+
        "\1\u0083\3\54\5\uffff\1\54\1\u00da\1\uffff\1\u00db\2\54\2\uffff"+
        "\1\54\1\u0083\2\uffff\1\54\1\u00e0\4\54\1\u00e5\1\54\1\u00e7\3\uffff"+
        "\1\54\2\uffff\2\54\1\u00ee\1\u00ef\1\uffff\1\u00f0\1\u00f1\1\u00f2"+
        "\1\54\1\uffff\1\54\2\uffff\1\161\1\uffff\2\54\1\u00f9\5\uffff\1"+
        "\u00fa\1\54\2\uffff\1\54\1\u00ff\2\uffff\1\u0100\2\uffff\1\u0103"+
        "\5\uffff\1\u00f6\1\uffff\1\161\2\u00f6";
    static final String DFA22_eofS =
        "\u0109\uffff";
    static final String DFA22_minS =
        "\1\11\1\75\1\40\1\46\2\uffff\1\75\1\53\1\uffff\1\55\3\uffff\1\74"+
        "\2\75\1\uffff\1\42\1\117\2\uffff\1\50\1\162\1\141\1\145\1\154\1"+
        "\141\1\157\1\144\1\157\1\145\1\150\1\162\1\156\1\150\1\uffff\1\75"+
        "\2\uffff\1\52\1\0\2\uffff\1\105\3\uffff\1\40\15\uffff\1\75\5\uffff"+
        "\1\75\1\uffff\1\141\3\uffff\1\155\1\uffff\1\162\1\145\1\150\1\uffff"+
        "\1\137\1\60\2\uffff\1\145\1\163\1\156\1\146\1\60\1\163\1\165\1\164"+
        "\1\162\1\154\1\164\3\60\1\156\1\164\1\157\1\141\1\151\1\160\1\165"+
        "\2\151\5\uffff\1\0\2\uffff\1\123\11\uffff\1\151\2\uffff\1\156\2"+
        "\uffff\1\111\1\uffff\1\141\1\145\1\163\1\141\1\uffff\1\145\1\155"+
        "\1\145\1\60\1\163\1\157\2\uffff\1\60\1\uffff\1\147\1\165\1\162\1"+
        "\164\1\165\1\164\2\145\1\157\1\151\1\154\1\0\1\60\2\uffff\1\160"+
        "\1\143\1\116\1\153\1\60\1\164\1\151\1\165\2\60\1\162\1\uffff\1\145"+
        "\1\60\1\uffff\1\60\1\162\1\164\1\151\2\143\1\144\1\60\1\156\1\147"+
        "\1\145\1\0\1\uffff\1\145\2\uffff\1\114\1\60\1\uffff\1\60\1\156\1"+
        "\154\2\uffff\1\156\1\60\2\uffff\1\156\1\60\1\143\1\164\1\150\1\145"+
        "\1\60\1\156\1\60\1\0\2\uffff\1\111\2\uffff\1\165\1\164\2\60\1\uffff"+
        "\3\60\1\146\1\uffff\1\145\1\uffff\3\0\1\116\1\145\1\60\5\uffff\1"+
        "\60\1\144\1\0\1\uffff\1\105\1\60\2\uffff\1\60\2\0\1\60\2\uffff\2"+
        "\0\1\uffff\3\0\1\12\1\0";
    static final String DFA22_maxS =
        "\1\176\1\75\1\uffff\1\75\2\uffff\2\75\1\uffff\1\76\3\uffff\2\75"+
        "\1\76\1\uffff\1\164\1\123\2\uffff\1\50\1\162\2\157\1\170\2\157\1"+
        "\156\1\157\1\145\1\167\1\171\1\156\1\150\1\uffff\1\174\2\uffff\1"+
        "\57\1\uffff\2\uffff\1\105\3\uffff\1\uffff\15\uffff\1\75\5\uffff"+
        "\1\75\1\uffff\1\154\3\uffff\1\156\1\uffff\1\165\1\171\1\162\1\uffff"+
        "\1\137\1\172\2\uffff\1\145\1\163\1\156\1\146\1\172\1\163\1\165\1"+
        "\164\1\162\1\154\1\164\3\172\1\156\1\164\1\157\1\162\1\151\1\160"+
        "\1\165\1\163\1\151\5\uffff\1\uffff\2\uffff\1\123\11\uffff\1\157"+
        "\2\uffff\1\156\2\uffff\1\111\1\uffff\1\141\1\145\1\164\1\141\1\uffff"+
        "\1\145\1\155\1\145\1\172\1\163\1\157\2\uffff\1\172\1\uffff\1\147"+
        "\1\165\1\162\1\164\1\165\1\164\2\145\1\157\1\151\1\154\1\uffff\1"+
        "\172\2\uffff\2\164\1\116\1\153\1\172\1\164\1\151\1\165\2\172\1\162"+
        "\1\uffff\1\145\1\172\1\uffff\1\172\1\162\1\164\1\151\2\143\1\144"+
        "\1\172\1\156\1\147\1\145\1\uffff\1\uffff\1\157\2\uffff\1\114\1\172"+
        "\1\uffff\1\172\1\156\1\154\2\uffff\1\156\1\172\2\uffff\1\156\1\172"+
        "\1\143\1\164\1\150\1\145\1\172\1\156\1\172\1\uffff\2\uffff\1\111"+
        "\2\uffff\1\165\1\164\2\172\1\uffff\3\172\1\146\1\uffff\1\145\1\uffff"+
        "\3\uffff\1\116\1\145\1\172\5\uffff\1\172\1\144\1\uffff\1\uffff\1"+
        "\105\1\172\2\uffff\1\172\2\uffff\1\172\2\uffff\2\uffff\1\uffff\3"+
        "\uffff\1\15\1\uffff";
    static final String DFA22_acceptS =
        "\4\uffff\1\7\1\10\2\uffff\1\16\1\uffff\1\23\1\24\1\25\3\uffff\1"+
        "\40\2\uffff\1\64\1\65\16\uffff\1\121\1\uffff\1\125\1\126\2\uffff"+
        "\1\134\1\135\1\uffff\1\141\1\2\1\1\1\uffff\1\136\1\4\1\6\1\5\1\12"+
        "\1\11\1\14\1\15\1\13\1\20\1\21\1\22\1\17\1\uffff\1\31\1\26\1\33"+
        "\1\32\1\35\1\uffff\1\34\1\uffff\1\43\1\44\1\45\1\uffff\1\50\3\uffff"+
        "\1\137\2\uffff\1\67\1\66\27\uffff\1\123\1\124\1\122\1\127\1\130"+
        "\1\uffff\1\132\1\133\1\uffff\1\3\1\30\1\27\1\37\1\36\1\41\1\42\1"+
        "\46\1\47\1\uffff\1\55\1\56\1\uffff\1\61\1\62\1\uffff\1\140\4\uffff"+
        "\1\75\6\uffff\1\103\1\104\1\uffff\1\105\15\uffff\1\3\1\51\13\uffff"+
        "\1\101\2\uffff\1\106\14\uffff\1\52\1\uffff\1\57\1\60\2\uffff\1\71"+
        "\3\uffff\1\76\1\77\2\uffff\1\102\1\107\12\uffff\1\53\1\54\1\uffff"+
        "\1\70\1\72\4\uffff\1\111\4\uffff\1\116\1\uffff\1\120\6\uffff\1\100"+
        "\1\110\1\112\1\113\1\114\3\uffff\1\131\2\uffff\1\74\1\115\4\uffff"+
        "\1\73\1\117\2\uffff\1\63\5\uffff";
    static final String DFA22_specialS =
        "\50\uffff\1\10\106\uffff\1\2\56\uffff\1\17\35\uffff\1\16\31\uffff"+
        "\1\3\21\uffff\1\6\1\4\1\20\12\uffff\1\5\6\uffff\1\15\1\12\3\uffff"+
        "\1\11\1\13\1\uffff\1\0\1\14\1\7\1\uffff\1\1}>";
    static final String[] DFA22_transitionS = {
            "\2\52\1\uffff\2\52\22\uffff\1\52\1\1\1\2\1\50\2\uffff\1\3\1"+
            "\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\47\12\51\1\13\1\14\1"+
            "\15\1\16\1\17\1\20\1\21\15\54\1\22\12\54\1\53\1\54\1\23\1\uffff"+
            "\1\24\1\25\1\54\1\uffff\1\54\1\26\1\27\1\30\1\31\1\32\1\33\1"+
            "\54\1\34\2\54\1\35\5\54\1\36\1\37\1\40\1\41\1\54\1\42\3\54\1"+
            "\43\1\44\1\45\1\46",
            "\1\55",
            "\43\60\1\57\uffbc\60",
            "\1\61\26\uffff\1\62",
            "",
            "",
            "\1\64",
            "\1\66\21\uffff\1\67",
            "",
            "\1\71\2\uffff\12\51\3\uffff\1\72\1\73",
            "",
            "",
            "",
            "\1\75\1\76",
            "\1\100",
            "\1\102\1\103",
            "",
            "\1\116\100\uffff\1\105\1\106\1\107\1\110\2\uffff\1\111\5\uffff"+
            "\1\112\1\113\2\uffff\1\114\1\115",
            "\1\120\3\uffff\1\117",
            "",
            "",
            "\1\121",
            "\1\123",
            "\1\124\15\uffff\1\125",
            "\1\126\11\uffff\1\127",
            "\1\130\1\uffff\1\131\11\uffff\1\132",
            "\1\134\15\uffff\1\133",
            "\1\135",
            "\1\136\1\uffff\1\137\7\uffff\1\140",
            "\1\141",
            "\1\142",
            "\1\143\13\uffff\1\144\2\uffff\1\145",
            "\1\147\6\uffff\1\146",
            "\1\150",
            "\1\151",
            "",
            "\1\152\76\uffff\1\153",
            "",
            "",
            "\1\156\4\uffff\1\155",
            "\144\161\1\160\4\161\1\157\uff96\161",
            "",
            "",
            "\1\162",
            "",
            "",
            "",
            "\2\60\1\163\uffdd\60",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\164",
            "",
            "",
            "",
            "",
            "",
            "\1\166",
            "",
            "\1\170\12\uffff\1\171",
            "",
            "",
            "",
            "\1\172\1\173",
            "",
            "\1\174\2\uffff\1\175",
            "\1\176\23\uffff\1\177",
            "\1\u0080\11\uffff\1\u0081",
            "",
            "\1\u0082",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\23\54\1\u0091\6\54",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096\20\uffff\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b\11\uffff\1\u009c",
            "\1\u009d",
            "",
            "",
            "",
            "",
            "",
            "\146\161\1\u009e\uff99\161",
            "",
            "",
            "\1\u009f",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00a1\5\uffff\1\u00a2",
            "",
            "",
            "\1\u00a3",
            "",
            "",
            "\1\u00a4",
            "",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7\1\u00a8",
            "\1\u00a9",
            "",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00ae",
            "\1\u00af",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\40\161\1\u00bc\uffdf\161",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\1\u00bd\3\uffff\1\u00be",
            "\1\u00bf\20\uffff\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00c9",
            "",
            "\1\u00ca",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\60\161\1\u00d6\uffcf\161",
            "",
            "\1\u00d7\11\uffff\1\u00d8",
            "",
            "",
            "\1\u00d9",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00dc",
            "\1\u00dd",
            "",
            "",
            "\1\u00de",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\1\u00df",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00e6",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\u00ea\1\u00e9\2\u00ea\1\u00e9\25\u00ea\1\u00e8\uffdc\u00ea",
            "",
            "",
            "\1\u00eb",
            "",
            "",
            "\1\u00ec",
            "\1\u00ed",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00f3",
            "",
            "\1\u00f4",
            "",
            "\12\u00ea\1\u00e9\2\u00ea\1\u00e9\25\u00ea\1\u00e8\101\u00ea"+
            "\1\u00f5\uff9a\u00ea",
            "\12\u00f6\1\u00e9\2\u00f6\1\u00e9\ufff2\u00f6",
            "\12\u00ea\1\u00e9\2\u00ea\1\u00e9\25\u00ea\1\u00e8\uffdc\u00ea",
            "\1\u00f7",
            "\1\u00f8",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00fb",
            "\12\u00ea\1\u00e9\2\u00ea\1\u00e9\25\u00ea\1\u00e8\110\u00ea"+
            "\1\u00fc\1\u00ea\1\u00fd\uff91\u00ea",
            "",
            "\1\u00fe",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\u00ea\1\u00e9\2\u00ea\1\u00e9\25\u00ea\1\u00e8\117\u00ea"+
            "\1\u0101\uff8c\u00ea",
            "\12\u00ea\1\u00e9\2\u00ea\1\u00e9\25\u00ea\1\u00e8\100\u00ea"+
            "\1\u0102\uff9b\u00ea",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\12\u00ea\1\u00e9\2\u00ea\1\u00e9\25\u00ea\1\u00e8\101\u00ea"+
            "\1\u0104\uff9a\u00ea",
            "\12\u00ea\1\u00e9\2\u00ea\1\u00e9\25\u00ea\1\u00e8\105\u00ea"+
            "\1\u0105\uff96\u00ea",
            "",
            "\12\u00ea\1\u0107\2\u00ea\1\u0106\25\u00ea\1\u00e8\uffdc\u00ea",
            "\12\u00ea\1\u00e9\2\u00ea\1\u00e9\25\u00ea\1\u00e8\102\u00ea"+
            "\1\u0108\uff99\u00ea",
            "\12\u00f6\1\u0107\2\u00f6\1\u00e9\ufff2\u00f6",
            "\1\u00e9\2\uffff\1\u00e9",
            "\12\u00ea\1\u0107\2\u00ea\1\u0106\25\u00ea\1\u00e8\uffdc\u00ea"
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | T__130 | T__131 | T__132 | T__133 | T__134 | T__135 | T__136 | T__137 | T__138 | T__139 | T__140 | T__141 | T__142 | T__143 | T__144 | T__145 | T__146 | T__147 | T__148 | T__149 | T__150 | T__151 | T__152 | T__153 | T__154 | T__155 | T__156 | T__157 | T__158 | T__159 | T__160 | T__161 | T__162 | T__163 | T__164 | T__165 | T__166 | T__167 | T__168 | T__169 | T__170 | T__171 | T__172 | T__173 | T__174 | T__175 | T__176 | T__177 | T__178 | T__179 | T__180 | T__181 | T__182 | T__183 | T__184 | T__185 | T__186 | T__187 | T__188 | T__189 | T__190 | T__191 | T__192 | T__193 | T__194 | T__195 | SINGLE_COMMENT_LIT | MULTI_COMMENT_LIT | IF0_COMMENT | DEFINE_LITERAL | PREPROCESSOR_DECLARATION | NUMBER_LITERAL | WHITESPACE | STRING_LITERAL | STRING_OBJC | BOOL_LITERAL | ID );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA22_260 = input.LA(1);

                        s = -1;
                        if ( (LA22_260=='\r') ) {s = 262;}

                        else if ( (LA22_260=='\n') ) {s = 263;}

                        else if ( (LA22_260=='#') ) {s = 232;}

                        else if ( ((LA22_260 >= '\u0000' && LA22_260 <= '\t')||(LA22_260 >= '\u000B' && LA22_260 <= '\f')||(LA22_260 >= '\u000E' && LA22_260 <= '\"')||(LA22_260 >= '$' && LA22_260 <= '\uFFFF')) ) {s = 234;}

                        else s = 246;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA22_264 = input.LA(1);

                        s = -1;
                        if ( (LA22_264=='\r') ) {s = 262;}

                        else if ( (LA22_264=='\n') ) {s = 263;}

                        else if ( (LA22_264=='#') ) {s = 232;}

                        else if ( ((LA22_264 >= '\u0000' && LA22_264 <= '\t')||(LA22_264 >= '\u000B' && LA22_264 <= '\f')||(LA22_264 >= '\u000E' && LA22_264 <= '\"')||(LA22_264 >= '$' && LA22_264 <= '\uFFFF')) ) {s = 234;}

                        else s = 246;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA22_111 = input.LA(1);

                        s = -1;
                        if ( (LA22_111=='f') ) {s = 158;}

                        else if ( ((LA22_111 >= '\u0000' && LA22_111 <= 'e')||(LA22_111 >= 'g' && LA22_111 <= '\uFFFF')) ) {s = 113;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA22_214 = input.LA(1);

                        s = -1;
                        if ( (LA22_214=='#') ) {s = 232;}

                        else if ( (LA22_214=='\n'||LA22_214=='\r') ) {s = 233;}

                        else if ( ((LA22_214 >= '\u0000' && LA22_214 <= '\t')||(LA22_214 >= '\u000B' && LA22_214 <= '\f')||(LA22_214 >= '\u000E' && LA22_214 <= '\"')||(LA22_214 >= '$' && LA22_214 <= '\uFFFF')) ) {s = 234;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA22_233 = input.LA(1);

                        s = -1;
                        if ( ((LA22_233 >= '\u0000' && LA22_233 <= '\t')||(LA22_233 >= '\u000B' && LA22_233 <= '\f')||(LA22_233 >= '\u000E' && LA22_233 <= '\uFFFF')) ) {s = 246;}

                        else if ( (LA22_233=='\n'||LA22_233=='\r') ) {s = 233;}

                        else s = 113;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA22_245 = input.LA(1);

                        s = -1;
                        if ( (LA22_245=='l') ) {s = 252;}

                        else if ( (LA22_245=='n') ) {s = 253;}

                        else if ( (LA22_245=='#') ) {s = 232;}

                        else if ( (LA22_245=='\n'||LA22_245=='\r') ) {s = 233;}

                        else if ( ((LA22_245 >= '\u0000' && LA22_245 <= '\t')||(LA22_245 >= '\u000B' && LA22_245 <= '\f')||(LA22_245 >= '\u000E' && LA22_245 <= '\"')||(LA22_245 >= '$' && LA22_245 <= 'k')||LA22_245=='m'||(LA22_245 >= 'o' && LA22_245 <= '\uFFFF')) ) {s = 234;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA22_232 = input.LA(1);

                        s = -1;
                        if ( (LA22_232=='e') ) {s = 245;}

                        else if ( (LA22_232=='#') ) {s = 232;}

                        else if ( (LA22_232=='\n'||LA22_232=='\r') ) {s = 233;}

                        else if ( ((LA22_232 >= '\u0000' && LA22_232 <= '\t')||(LA22_232 >= '\u000B' && LA22_232 <= '\f')||(LA22_232 >= '\u000E' && LA22_232 <= '\"')||(LA22_232 >= '$' && LA22_232 <= 'd')||(LA22_232 >= 'f' && LA22_232 <= '\uFFFF')) ) {s = 234;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA22_262 = input.LA(1);

                        s = -1;
                        if ( (LA22_262=='\n') ) {s = 263;}

                        else if ( ((LA22_262 >= '\u0000' && LA22_262 <= '\t')||(LA22_262 >= '\u000B' && LA22_262 <= '\f')||(LA22_262 >= '\u000E' && LA22_262 <= '\uFFFF')) ) {s = 246;}

                        else if ( (LA22_262=='\r') ) {s = 233;}

                        else s = 113;

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA22_40 = input.LA(1);

                        s = -1;
                        if ( (LA22_40=='i') ) {s = 111;}

                        else if ( (LA22_40=='d') ) {s = 112;}

                        else if ( ((LA22_40 >= '\u0000' && LA22_40 <= 'c')||(LA22_40 >= 'e' && LA22_40 <= 'h')||(LA22_40 >= 'j' && LA22_40 <= '\uFFFF')) ) {s = 113;}

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA22_257 = input.LA(1);

                        s = -1;
                        if ( (LA22_257=='e') ) {s = 260;}

                        else if ( (LA22_257=='#') ) {s = 232;}

                        else if ( (LA22_257=='\n'||LA22_257=='\r') ) {s = 233;}

                        else if ( ((LA22_257 >= '\u0000' && LA22_257 <= '\t')||(LA22_257 >= '\u000B' && LA22_257 <= '\f')||(LA22_257 >= '\u000E' && LA22_257 <= '\"')||(LA22_257 >= '$' && LA22_257 <= 'd')||(LA22_257 >= 'f' && LA22_257 <= '\uFFFF')) ) {s = 234;}

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA22_253 = input.LA(1);

                        s = -1;
                        if ( (LA22_253=='d') ) {s = 258;}

                        else if ( (LA22_253=='#') ) {s = 232;}

                        else if ( (LA22_253=='\n'||LA22_253=='\r') ) {s = 233;}

                        else if ( ((LA22_253 >= '\u0000' && LA22_253 <= '\t')||(LA22_253 >= '\u000B' && LA22_253 <= '\f')||(LA22_253 >= '\u000E' && LA22_253 <= '\"')||(LA22_253 >= '$' && LA22_253 <= 'c')||(LA22_253 >= 'e' && LA22_253 <= '\uFFFF')) ) {s = 234;}

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA22_258 = input.LA(1);

                        s = -1;
                        if ( (LA22_258=='i') ) {s = 261;}

                        else if ( (LA22_258=='#') ) {s = 232;}

                        else if ( (LA22_258=='\n'||LA22_258=='\r') ) {s = 233;}

                        else if ( ((LA22_258 >= '\u0000' && LA22_258 <= '\t')||(LA22_258 >= '\u000B' && LA22_258 <= '\f')||(LA22_258 >= '\u000E' && LA22_258 <= '\"')||(LA22_258 >= '$' && LA22_258 <= 'h')||(LA22_258 >= 'j' && LA22_258 <= '\uFFFF')) ) {s = 234;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA22_261 = input.LA(1);

                        s = -1;
                        if ( (LA22_261=='f') ) {s = 264;}

                        else if ( (LA22_261=='#') ) {s = 232;}

                        else if ( (LA22_261=='\n'||LA22_261=='\r') ) {s = 233;}

                        else if ( ((LA22_261 >= '\u0000' && LA22_261 <= '\t')||(LA22_261 >= '\u000B' && LA22_261 <= '\f')||(LA22_261 >= '\u000E' && LA22_261 <= '\"')||(LA22_261 >= '$' && LA22_261 <= 'e')||(LA22_261 >= 'g' && LA22_261 <= '\uFFFF')) ) {s = 234;}

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA22_252 = input.LA(1);

                        s = -1;
                        if ( (LA22_252=='s') ) {s = 257;}

                        else if ( (LA22_252=='#') ) {s = 232;}

                        else if ( (LA22_252=='\n'||LA22_252=='\r') ) {s = 233;}

                        else if ( ((LA22_252 >= '\u0000' && LA22_252 <= '\t')||(LA22_252 >= '\u000B' && LA22_252 <= '\f')||(LA22_252 >= '\u000E' && LA22_252 <= '\"')||(LA22_252 >= '$' && LA22_252 <= 'r')||(LA22_252 >= 't' && LA22_252 <= '\uFFFF')) ) {s = 234;}

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA22_188 = input.LA(1);

                        s = -1;
                        if ( (LA22_188=='0') ) {s = 214;}

                        else if ( ((LA22_188 >= '\u0000' && LA22_188 <= '/')||(LA22_188 >= '1' && LA22_188 <= '\uFFFF')) ) {s = 113;}

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA22_158 = input.LA(1);

                        s = -1;
                        if ( (LA22_158==' ') ) {s = 188;}

                        else if ( ((LA22_158 >= '\u0000' && LA22_158 <= '\u001F')||(LA22_158 >= '!' && LA22_158 <= '\uFFFF')) ) {s = 113;}

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA22_234 = input.LA(1);

                        s = -1;
                        if ( (LA22_234=='#') ) {s = 232;}

                        else if ( (LA22_234=='\n'||LA22_234=='\r') ) {s = 233;}

                        else if ( ((LA22_234 >= '\u0000' && LA22_234 <= '\t')||(LA22_234 >= '\u000B' && LA22_234 <= '\f')||(LA22_234 >= '\u000E' && LA22_234 <= '\"')||(LA22_234 >= '$' && LA22_234 <= '\uFFFF')) ) {s = 234;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 22, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}