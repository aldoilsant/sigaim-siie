// Generated from SEQL.g4 by ANTLR 4.2.2
package org.sigaim.siie.seql.parser.generated;

import org.sigaim.siie.seql.parser.model.SEQLFromCondition.SEQLFromComponent;
import org.sigaim.siie.seql.parser.model.SEQLOperation;
import org.sigaim.siie.seql.parser.model.SEQLEvaluable;
import org.sigaim.siie.seql.parser.model.SEQLPrimitive;
import org.sigaim.siie.seql.parser.model.SEQLPath;



import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SEQLLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, WS=5, NODEID=6, FROM=7, CONTAINS=8, SELECT=9, 
		WHERE=10, EHR=11, COMMA=12, FORWARD=13, BACKWARD=14, TOP=15, INTEGER=16, 
		BOOLEAN=17, AND=18, OR=19, XOR=20, NOT=21, AS=22, COMPARABLEOPERATOR=23, 
		EXISTS=24, STRING=25, ARCHETYPEID=26, IDENTIFIER=27, OPENBRACKET=28, CLOSEBRACKET=29;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"')'", "'('", "'/'", "';'", "WS", "NODEID", "'FROM'", "'CONTAINS'", "'SELECT'", 
		"'WHERE'", "EHR", "','", "FORWARD", "BACKWARD", "TOP", "INTEGER", "BOOLEAN", 
		"AND", "OR", "XOR", "NOT", "AS", "COMPARABLEOPERATOR", "EXISTS", "STRING", 
		"ARCHETYPEID", "IDENTIFIER", "'['", "']'"
	};
	public static final String[] ruleNames = {
		"T__3", "T__2", "T__1", "T__0", "WS", "NODEID", "FROM", "CONTAINS", "SELECT", 
		"WHERE", "EHR", "COMMA", "FORWARD", "BACKWARD", "TOP", "INTEGER", "BOOLEAN", 
		"AND", "OR", "XOR", "NOT", "AS", "COMPARABLEOPERATOR", "EXISTS", "STRING", 
		"ARCHETYPEID", "IDENTIFIER", "LETTERMINUSA", "LETTER", "IDCHAR", "ALPHANUM", 
		"DIGIT", "ESC_SEQ", "UNICODE_ESC", "HEX_DIGIT", "OPENBRACKET", "CLOSEBRACKET"
	};


	public SEQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SEQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\37\u0153\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\6\6W\n\6\r\6\16\6X\3\6\3\6\3\7\3\7\3\7\3\7\6\7a\n\7\r\7\16\7b\3"+
		"\7\3\7\6\7g\n\7\r\7\16\7h\7\7k\n\7\f\7\16\7n\13\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\21\5\21\u00a7\n\21\3\21\6\21\u00aa\n\21\r\21\16\21\u00ab"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\5\22\u00c0\n\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00dd\n\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\7\32\u00e9\n\32\f\32\16\32\u00ec\13\32\3\32"+
		"\3\32\3\32\3\32\7\32\u00f2\n\32\f\32\16\32\u00f5\13\32\3\32\5\32\u00f8"+
		"\n\32\3\33\6\33\u00fb\n\33\r\33\16\33\u00fc\3\33\3\33\6\33\u0101\n\33"+
		"\r\33\16\33\u0102\3\33\3\33\3\33\6\33\u0108\n\33\r\33\16\33\u0109\3\33"+
		"\3\33\3\33\6\33\u010f\n\33\r\33\16\33\u0110\3\33\3\33\3\33\3\33\6\33\u0117"+
		"\n\33\r\33\16\33\u0118\3\33\3\33\6\33\u011d\n\33\r\33\16\33\u011e\5\33"+
		"\u0121\n\33\3\34\3\34\3\34\7\34\u0126\n\34\f\34\16\34\u0129\13\34\3\34"+
		"\3\34\7\34\u012d\n\34\f\34\16\34\u0130\13\34\5\34\u0132\n\34\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\5\37\u013a\n\37\3 \3 \5 \u013e\n \3!\3!\3\"\3\"\3"+
		"\"\5\"\u0145\n\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3%\3%\3&\3&\2\2\'\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\2;\2=\2?\2A\2C"+
		"\2E\2G\2I\36K\37\3\2\32\5\2\13\f\17\17\"\"\4\2GGgg\4\2JJjj\4\2TTtt\4\2"+
		"HHhh\4\2QQqq\4\2YYyy\4\2CCcc\4\2FFff\4\2DDdd\4\2EEee\4\2MMmm\4\2VVvv\4"+
		"\2RRrr\4\2PPpp\4\2ZZzz\4\2UUuu\4\2KKkk\4\2))^^\4\2$$^^\4\2D\\d|\4\2C\\"+
		"c|\n\2$$))^^ddhhppttvv\5\2\62;CHch\u016d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\3M\3\2\2\2\5O\3\2\2\2\7"+
		"Q\3\2\2\2\tS\3\2\2\2\13V\3\2\2\2\r\\\3\2\2\2\17o\3\2\2\2\21t\3\2\2\2\23"+
		"}\3\2\2\2\25\u0084\3\2\2\2\27\u008a\3\2\2\2\31\u008e\3\2\2\2\33\u0090"+
		"\3\2\2\2\35\u0098\3\2\2\2\37\u00a1\3\2\2\2!\u00a6\3\2\2\2#\u00bf\3\2\2"+
		"\2%\u00c1\3\2\2\2\'\u00c5\3\2\2\2)\u00c8\3\2\2\2+\u00cc\3\2\2\2-\u00d0"+
		"\3\2\2\2/\u00dc\3\2\2\2\61\u00de\3\2\2\2\63\u00f7\3\2\2\2\65\u00fa\3\2"+
		"\2\2\67\u0131\3\2\2\29\u0133\3\2\2\2;\u0135\3\2\2\2=\u0139\3\2\2\2?\u013d"+
		"\3\2\2\2A\u013f\3\2\2\2C\u0144\3\2\2\2E\u0146\3\2\2\2G\u014d\3\2\2\2I"+
		"\u014f\3\2\2\2K\u0151\3\2\2\2MN\7+\2\2N\4\3\2\2\2OP\7*\2\2P\6\3\2\2\2"+
		"QR\7\61\2\2R\b\3\2\2\2ST\7=\2\2T\n\3\2\2\2UW\t\2\2\2VU\3\2\2\2WX\3\2\2"+
		"\2XV\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\b\6\2\2[\f\3\2\2\2\\]\7c\2\2]^\7v\2"+
		"\2^`\3\2\2\2_a\5A!\2`_\3\2\2\2ab\3\2\2\2b`\3\2\2\2bc\3\2\2\2cl\3\2\2\2"+
		"df\7\60\2\2eg\5A!\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2"+
		"jd\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\16\3\2\2\2nl\3\2\2\2op\7H\2"+
		"\2pq\7T\2\2qr\7Q\2\2rs\7O\2\2s\20\3\2\2\2tu\7E\2\2uv\7Q\2\2vw\7P\2\2w"+
		"x\7V\2\2xy\7C\2\2yz\7K\2\2z{\7P\2\2{|\7U\2\2|\22\3\2\2\2}~\7U\2\2~\177"+
		"\7G\2\2\177\u0080\7N\2\2\u0080\u0081\7G\2\2\u0081\u0082\7E\2\2\u0082\u0083"+
		"\7V\2\2\u0083\24\3\2\2\2\u0084\u0085\7Y\2\2\u0085\u0086\7J\2\2\u0086\u0087"+
		"\7G\2\2\u0087\u0088\7T\2\2\u0088\u0089\7G\2\2\u0089\26\3\2\2\2\u008a\u008b"+
		"\t\3\2\2\u008b\u008c\t\4\2\2\u008c\u008d\t\5\2\2\u008d\30\3\2\2\2\u008e"+
		"\u008f\7.\2\2\u008f\32\3\2\2\2\u0090\u0091\t\6\2\2\u0091\u0092\t\7\2\2"+
		"\u0092\u0093\t\5\2\2\u0093\u0094\t\b\2\2\u0094\u0095\t\t\2\2\u0095\u0096"+
		"\t\5\2\2\u0096\u0097\t\n\2\2\u0097\34\3\2\2\2\u0098\u0099\t\13\2\2\u0099"+
		"\u009a\t\t\2\2\u009a\u009b\t\f\2\2\u009b\u009c\t\r\2\2\u009c\u009d\t\b"+
		"\2\2\u009d\u009e\t\t\2\2\u009e\u009f\t\5\2\2\u009f\u00a0\t\n\2\2\u00a0"+
		"\36\3\2\2\2\u00a1\u00a2\t\16\2\2\u00a2\u00a3\t\7\2\2\u00a3\u00a4\t\17"+
		"\2\2\u00a4 \3\2\2\2\u00a5\u00a7\7/\2\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7"+
		"\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00aa\5A!\2\u00a9\u00a8\3\2\2\2\u00aa"+
		"\u00ab\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\"\3\2\2\2"+
		"\u00ad\u00ae\7v\2\2\u00ae\u00af\7t\2\2\u00af\u00b0\7w\2\2\u00b0\u00c0"+
		"\7g\2\2\u00b1\u00b2\7h\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7n\2\2\u00b4"+
		"\u00b5\7u\2\2\u00b5\u00c0\7g\2\2\u00b6\u00b7\7V\2\2\u00b7\u00b8\7T\2\2"+
		"\u00b8\u00b9\7W\2\2\u00b9\u00c0\7G\2\2\u00ba\u00bb\7H\2\2\u00bb\u00bc"+
		"\7C\2\2\u00bc\u00bd\7N\2\2\u00bd\u00be\7U\2\2\u00be\u00c0\7G\2\2\u00bf"+
		"\u00ad\3\2\2\2\u00bf\u00b1\3\2\2\2\u00bf\u00b6\3\2\2\2\u00bf\u00ba\3\2"+
		"\2\2\u00c0$\3\2\2\2\u00c1\u00c2\t\t\2\2\u00c2\u00c3\t\20\2\2\u00c3\u00c4"+
		"\t\n\2\2\u00c4&\3\2\2\2\u00c5\u00c6\t\7\2\2\u00c6\u00c7\t\5\2\2\u00c7"+
		"(\3\2\2\2\u00c8\u00c9\t\21\2\2\u00c9\u00ca\t\7\2\2\u00ca\u00cb\t\5\2\2"+
		"\u00cb*\3\2\2\2\u00cc\u00cd\t\20\2\2\u00cd\u00ce\t\7\2\2\u00ce\u00cf\t"+
		"\16\2\2\u00cf,\3\2\2\2\u00d0\u00d1\t\t\2\2\u00d1\u00d2\t\22\2\2\u00d2"+
		".\3\2\2\2\u00d3\u00dd\7?\2\2\u00d4\u00d5\7#\2\2\u00d5\u00dd\7?\2\2\u00d6"+
		"\u00dd\7@\2\2\u00d7\u00d8\7@\2\2\u00d8\u00dd\7?\2\2\u00d9\u00dd\7>\2\2"+
		"\u00da\u00db\7>\2\2\u00db\u00dd\7?\2\2\u00dc\u00d3\3\2\2\2\u00dc\u00d4"+
		"\3\2\2\2\u00dc\u00d6\3\2\2\2\u00dc\u00d7\3\2\2\2\u00dc\u00d9\3\2\2\2\u00dc"+
		"\u00da\3\2\2\2\u00dd\60\3\2\2\2\u00de\u00df\t\3\2\2\u00df\u00e0\t\21\2"+
		"\2\u00e0\u00e1\t\23\2\2\u00e1\u00e2\t\22\2\2\u00e2\u00e3\t\16\2\2\u00e3"+
		"\u00e4\t\22\2\2\u00e4\62\3\2\2\2\u00e5\u00ea\7)\2\2\u00e6\u00e9\5C\"\2"+
		"\u00e7\u00e9\n\24\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ec"+
		"\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ed\u00f8\7)\2\2\u00ee\u00f3\7$\2\2\u00ef\u00f2\5C\""+
		"\2\u00f0\u00f2\n\25\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f0\3\2\2\2\u00f2"+
		"\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f6\3\2"+
		"\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f8\7$\2\2\u00f7\u00e5\3\2\2\2\u00f7"+
		"\u00ee\3\2\2\2\u00f8\64\3\2\2\2\u00f9\u00fb\5? \2\u00fa\u00f9\3\2\2\2"+
		"\u00fb\u00fc\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe"+
		"\3\2\2\2\u00fe\u0100\7/\2\2\u00ff\u0101\5? \2\u0100\u00ff\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2"+
		"\2\2\u0104\u0107\7/\2\2\u0105\u0108\5;\36\2\u0106\u0108\7a\2\2\u0107\u0105"+
		"\3\2\2\2\u0107\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010e\7\60\2\2\u010c\u010f\5"+
		"=\37\2\u010d\u010f\7/\2\2\u010e\u010c\3\2\2\2\u010e\u010d\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112\3\2"+
		"\2\2\u0112\u0113\7\60\2\2\u0113\u0114\7x\2\2\u0114\u0116\3\2\2\2\u0115"+
		"\u0117\5A!\2\u0116\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0116\3\2\2"+
		"\2\u0118\u0119\3\2\2\2\u0119\u0120\3\2\2\2\u011a\u011c\7\60\2\2\u011b"+
		"\u011d\5A!\2\u011c\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011c\3\2\2"+
		"\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u011a\3\2\2\2\u0120\u0121"+
		"\3\2\2\2\u0121\66\3\2\2\2\u0122\u0127\t\t\2\2\u0123\u0126\5? \2\u0124"+
		"\u0126\7a\2\2\u0125\u0123\3\2\2\2\u0125\u0124\3\2\2\2\u0126\u0129\3\2"+
		"\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0132\3\2\2\2\u0129"+
		"\u0127\3\2\2\2\u012a\u012e\59\35\2\u012b\u012d\5=\37\2\u012c\u012b\3\2"+
		"\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0122\3\2\2\2\u0131\u012a\3\2"+
		"\2\2\u01328\3\2\2\2\u0133\u0134\t\26\2\2\u0134:\3\2\2\2\u0135\u0136\t"+
		"\27\2\2\u0136<\3\2\2\2\u0137\u013a\5? \2\u0138\u013a\7a\2\2\u0139\u0137"+
		"\3\2\2\2\u0139\u0138\3\2\2\2\u013a>\3\2\2\2\u013b\u013e\5;\36\2\u013c"+
		"\u013e\5A!\2\u013d\u013b\3\2\2\2\u013d\u013c\3\2\2\2\u013e@\3\2\2\2\u013f"+
		"\u0140\4\62;\2\u0140B\3\2\2\2\u0141\u0142\7^\2\2\u0142\u0145\t\30\2\2"+
		"\u0143\u0145\5E#\2\u0144\u0141\3\2\2\2\u0144\u0143\3\2\2\2\u0145D\3\2"+
		"\2\2\u0146\u0147\7^\2\2\u0147\u0148\7w\2\2\u0148\u0149\5G$\2\u0149\u014a"+
		"\5G$\2\u014a\u014b\5G$\2\u014b\u014c\5G$\2\u014cF\3\2\2\2\u014d\u014e"+
		"\t\31\2\2\u014eH\3\2\2\2\u014f\u0150\7]\2\2\u0150J\3\2\2\2\u0151\u0152"+
		"\7_\2\2\u0152L\3\2\2\2 \2Xbhl\u00a6\u00ab\u00bf\u00dc\u00e8\u00ea\u00f1"+
		"\u00f3\u00f7\u00fc\u0102\u0107\u0109\u010e\u0110\u0118\u011e\u0120\u0125"+
		"\u0127\u012e\u0131\u0139\u013d\u0144\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}