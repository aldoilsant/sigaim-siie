// Generated from org/sigaim/siie/seql/parser/generated/SEQL.g4 by ANTLR 4.2

package org.sigaim.siie.seql.parser.generated;
import org.sigaim.siie.seql.model.SEQLFromComponent;
import org.sigaim.siie.seql.model.SEQLOperation;
import org.sigaim.siie.seql.model.SEQLEvaluable;
import org.sigaim.siie.seql.model.SEQLPrimitive;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.seql.model.SEQLPathPredicate;

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
		WHERE=10, HAVING=11, EHR=12, SYSTEM=13, COMMA=14, FORWARD=15, BACKWARD=16, 
		TOP=17, INTEGER=18, FLOAT=19, DATE=20, BOOLEAN=21, AND=22, OR=23, XOR=24, 
		NOT=25, AS=26, ALL=27, VERSIONS=28, OF=29, WITH=30, DESCENDANTS=31, MERGED=32, 
		COMPARABLEOPERATOR=33, EXISTS=34, STRING=35, ARCHETYPEID=36, IDENTIFIER=37, 
		OPENBRACKET=38, CLOSEBRACKET=39;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"')'", "'('", "'/'", "';'", "WS", "NODEID", "'FROM'", "'CONTAINS'", "'SELECT'", 
		"'WHERE'", "'HAVING'", "'EHR'", "'SYSTEM'", "','", "FORWARD", "BACKWARD", 
		"TOP", "INTEGER", "FLOAT", "DATE", "BOOLEAN", "AND", "OR", "XOR", "NOT", 
		"AS", "'ALL'", "'VERSIONS'", "'OF'", "'WITH'", "'DESCENDANTS'", "'MERGED'", 
		"COMPARABLEOPERATOR", "EXISTS", "STRING", "ARCHETYPEID", "IDENTIFIER", 
		"'['", "']'"
	};
	public static final String[] ruleNames = {
		"T__3", "T__2", "T__1", "T__0", "WS", "NODEID", "FROM", "CONTAINS", "SELECT", 
		"WHERE", "HAVING", "EHR", "SYSTEM", "COMMA", "FORWARD", "BACKWARD", "TOP", 
		"INTEGER", "FLOAT", "DATE", "BOOLEAN", "AND", "OR", "XOR", "NOT", "AS", 
		"ALL", "VERSIONS", "OF", "WITH", "DESCENDANTS", "MERGED", "COMPARABLEOPERATOR", 
		"EXISTS", "STRING", "ARCHETYPEID", "IDENTIFIER", "LETTERMINUSA", "LETTER", 
		"IDCHAR", "ALPHANUM", "DIGIT", "ESC_SEQ", "UNICODE_ESC", "HEX_DIGIT", 
		"OPENBRACKET", "CLOSEBRACKET"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2)\u01cb\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\6"+
		"\6k\n\6\r\6\16\6l\3\6\3\6\3\7\3\7\3\7\3\7\6\7u\n\7\r\7\16\7v\3\7\3\7\6"+
		"\7{\n\7\r\7\16\7|\7\7\177\n\7\f\7\16\7\u0082\13\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\23\5\23\u00c9\n\23\3\23\6\23\u00cc\n\23\r\23\16\23\u00cd"+
		"\3\24\5\24\u00d1\n\24\3\24\6\24\u00d4\n\24\r\24\16\24\u00d5\3\24\3\24"+
		"\6\24\u00da\n\24\r\24\16\24\u00db\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\5\26\u0110\n\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37"+
		"\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u0155\n\"\3#\3#\3#\3#\3#\3#\3#\3$\3$"+
		"\3$\7$\u0161\n$\f$\16$\u0164\13$\3$\3$\3$\3$\7$\u016a\n$\f$\16$\u016d"+
		"\13$\3$\5$\u0170\n$\3%\6%\u0173\n%\r%\16%\u0174\3%\3%\6%\u0179\n%\r%\16"+
		"%\u017a\3%\3%\3%\6%\u0180\n%\r%\16%\u0181\3%\3%\3%\6%\u0187\n%\r%\16%"+
		"\u0188\3%\3%\3%\3%\6%\u018f\n%\r%\16%\u0190\3%\3%\6%\u0195\n%\r%\16%\u0196"+
		"\5%\u0199\n%\3&\3&\3&\7&\u019e\n&\f&\16&\u01a1\13&\3&\3&\7&\u01a5\n&\f"+
		"&\16&\u01a8\13&\5&\u01aa\n&\3\'\3\'\3(\3(\3)\3)\5)\u01b2\n)\3*\3*\5*\u01b6"+
		"\n*\3+\3+\3,\3,\3,\5,\u01bd\n,\3-\3-\3-\3-\3-\3-\3-\3.\3.\3/\3/\3\60\3"+
		"\60\2\2\61\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%I&K\'M\2O\2Q\2S\2U\2W\2Y\2[\2](_)\3\2\31\5\2\13"+
		"\f\17\17\"\"\4\2HHhh\4\2QQqq\4\2TTtt\4\2YYyy\4\2CCcc\4\2FFff\4\2DDdd\4"+
		"\2EEee\4\2MMmm\4\2VVvv\4\2RRrr\4\2PPpp\4\2ZZzz\4\2UUuu\4\2GGgg\4\2KKk"+
		"k\4\2))^^\4\2$$^^\4\2D\\d|\4\2C\\c|\n\2$$))^^ddhhppttvv\5\2\62;CHch\u01e8"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3a\3\2\2\2\5c\3\2\2\2\7"+
		"e\3\2\2\2\tg\3\2\2\2\13j\3\2\2\2\rp\3\2\2\2\17\u0083\3\2\2\2\21\u0088"+
		"\3\2\2\2\23\u0091\3\2\2\2\25\u0098\3\2\2\2\27\u009e\3\2\2\2\31\u00a5\3"+
		"\2\2\2\33\u00a9\3\2\2\2\35\u00b0\3\2\2\2\37\u00b2\3\2\2\2!\u00ba\3\2\2"+
		"\2#\u00c3\3\2\2\2%\u00c8\3\2\2\2\'\u00d0\3\2\2\2)\u00dd\3\2\2\2+\u010f"+
		"\3\2\2\2-\u0111\3\2\2\2/\u0115\3\2\2\2\61\u0118\3\2\2\2\63\u011c\3\2\2"+
		"\2\65\u0120\3\2\2\2\67\u0123\3\2\2\29\u0127\3\2\2\2;\u0130\3\2\2\2=\u0133"+
		"\3\2\2\2?\u0138\3\2\2\2A\u0144\3\2\2\2C\u0154\3\2\2\2E\u0156\3\2\2\2G"+
		"\u016f\3\2\2\2I\u0172\3\2\2\2K\u01a9\3\2\2\2M\u01ab\3\2\2\2O\u01ad\3\2"+
		"\2\2Q\u01b1\3\2\2\2S\u01b5\3\2\2\2U\u01b7\3\2\2\2W\u01bc\3\2\2\2Y\u01be"+
		"\3\2\2\2[\u01c5\3\2\2\2]\u01c7\3\2\2\2_\u01c9\3\2\2\2ab\7+\2\2b\4\3\2"+
		"\2\2cd\7*\2\2d\6\3\2\2\2ef\7\61\2\2f\b\3\2\2\2gh\7=\2\2h\n\3\2\2\2ik\t"+
		"\2\2\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2mn\3\2\2\2no\b\6\2\2o\f"+
		"\3\2\2\2pq\7c\2\2qr\7v\2\2rt\3\2\2\2su\5U+\2ts\3\2\2\2uv\3\2\2\2vt\3\2"+
		"\2\2vw\3\2\2\2w\u0080\3\2\2\2xz\7\60\2\2y{\5U+\2zy\3\2\2\2{|\3\2\2\2|"+
		"z\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~x\3\2\2\2\177\u0082\3\2\2\2\u0080~\3"+
		"\2\2\2\u0080\u0081\3\2\2\2\u0081\16\3\2\2\2\u0082\u0080\3\2\2\2\u0083"+
		"\u0084\7H\2\2\u0084\u0085\7T\2\2\u0085\u0086\7Q\2\2\u0086\u0087\7O\2\2"+
		"\u0087\20\3\2\2\2\u0088\u0089\7E\2\2\u0089\u008a\7Q\2\2\u008a\u008b\7"+
		"P\2\2\u008b\u008c\7V\2\2\u008c\u008d\7C\2\2\u008d\u008e\7K\2\2\u008e\u008f"+
		"\7P\2\2\u008f\u0090\7U\2\2\u0090\22\3\2\2\2\u0091\u0092\7U\2\2\u0092\u0093"+
		"\7G\2\2\u0093\u0094\7N\2\2\u0094\u0095\7G\2\2\u0095\u0096\7E\2\2\u0096"+
		"\u0097\7V\2\2\u0097\24\3\2\2\2\u0098\u0099\7Y\2\2\u0099\u009a\7J\2\2\u009a"+
		"\u009b\7G\2\2\u009b\u009c\7T\2\2\u009c\u009d\7G\2\2\u009d\26\3\2\2\2\u009e"+
		"\u009f\7J\2\2\u009f\u00a0\7C\2\2\u00a0\u00a1\7X\2\2\u00a1\u00a2\7K\2\2"+
		"\u00a2\u00a3\7P\2\2\u00a3\u00a4\7I\2\2\u00a4\30\3\2\2\2\u00a5\u00a6\7"+
		"G\2\2\u00a6\u00a7\7J\2\2\u00a7\u00a8\7T\2\2\u00a8\32\3\2\2\2\u00a9\u00aa"+
		"\7U\2\2\u00aa\u00ab\7[\2\2\u00ab\u00ac\7U\2\2\u00ac\u00ad\7V\2\2\u00ad"+
		"\u00ae\7G\2\2\u00ae\u00af\7O\2\2\u00af\34\3\2\2\2\u00b0\u00b1\7.\2\2\u00b1"+
		"\36\3\2\2\2\u00b2\u00b3\t\3\2\2\u00b3\u00b4\t\4\2\2\u00b4\u00b5\t\5\2"+
		"\2\u00b5\u00b6\t\6\2\2\u00b6\u00b7\t\7\2\2\u00b7\u00b8\t\5\2\2\u00b8\u00b9"+
		"\t\b\2\2\u00b9 \3\2\2\2\u00ba\u00bb\t\t\2\2\u00bb\u00bc\t\7\2\2\u00bc"+
		"\u00bd\t\n\2\2\u00bd\u00be\t\13\2\2\u00be\u00bf\t\6\2\2\u00bf\u00c0\t"+
		"\7\2\2\u00c0\u00c1\t\5\2\2\u00c1\u00c2\t\b\2\2\u00c2\"\3\2\2\2\u00c3\u00c4"+
		"\t\f\2\2\u00c4\u00c5\t\4\2\2\u00c5\u00c6\t\r\2\2\u00c6$\3\2\2\2\u00c7"+
		"\u00c9\7/\2\2\u00c8\u00c7\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cb\3\2"+
		"\2\2\u00ca\u00cc\5U+\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cb"+
		"\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce&\3\2\2\2\u00cf\u00d1\7/\2\2\u00d0\u00cf"+
		"\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\3\2\2\2\u00d2\u00d4\5U+\2\u00d3"+
		"\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2"+
		"\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\7\60\2\2\u00d8\u00da\5U+\2\u00d9"+
		"\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2"+
		"\2\2\u00dc(\3\2\2\2\u00dd\u00de\7)\2\2\u00de\u00df\5U+\2\u00df\u00e0\5"+
		"U+\2\u00e0\u00e1\5U+\2\u00e1\u00e2\5U+\2\u00e2\u00e3\7/\2\2\u00e3\u00e4"+
		"\5U+\2\u00e4\u00e5\5U+\2\u00e5\u00e6\7/\2\2\u00e6\u00e7\5U+\2\u00e7\u00e8"+
		"\5U+\2\u00e8\u00e9\7V\2\2\u00e9\u00ea\5U+\2\u00ea\u00eb\5U+\2\u00eb\u00ec"+
		"\7<\2\2\u00ec\u00ed\5U+\2\u00ed\u00ee\5U+\2\u00ee\u00ef\7<\2\2\u00ef\u00f0"+
		"\5U+\2\u00f0\u00f1\5U+\2\u00f1\u00f2\7\60\2\2\u00f2\u00f3\5U+\2\u00f3"+
		"\u00f4\5U+\2\u00f4\u00f5\5U+\2\u00f5\u00f6\7-\2\2\u00f6\u00f7\5U+\2\u00f7"+
		"\u00f8\5U+\2\u00f8\u00f9\7<\2\2\u00f9\u00fa\5U+\2\u00fa\u00fb\5U+\2\u00fb"+
		"\u00fc\7)\2\2\u00fc*\3\2\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\7t\2\2\u00ff"+
		"\u0100\7w\2\2\u0100\u0110\7g\2\2\u0101\u0102\7h\2\2\u0102\u0103\7c\2\2"+
		"\u0103\u0104\7n\2\2\u0104\u0105\7u\2\2\u0105\u0110\7g\2\2\u0106\u0107"+
		"\7V\2\2\u0107\u0108\7T\2\2\u0108\u0109\7W\2\2\u0109\u0110\7G\2\2\u010a"+
		"\u010b\7H\2\2\u010b\u010c\7C\2\2\u010c\u010d\7N\2\2\u010d\u010e\7U\2\2"+
		"\u010e\u0110\7G\2\2\u010f\u00fd\3\2\2\2\u010f\u0101\3\2\2\2\u010f\u0106"+
		"\3\2\2\2\u010f\u010a\3\2\2\2\u0110,\3\2\2\2\u0111\u0112\t\7\2\2\u0112"+
		"\u0113\t\16\2\2\u0113\u0114\t\b\2\2\u0114.\3\2\2\2\u0115\u0116\t\4\2\2"+
		"\u0116\u0117\t\5\2\2\u0117\60\3\2\2\2\u0118\u0119\t\17\2\2\u0119\u011a"+
		"\t\4\2\2\u011a\u011b\t\5\2\2\u011b\62\3\2\2\2\u011c\u011d\t\16\2\2\u011d"+
		"\u011e\t\4\2\2\u011e\u011f\t\f\2\2\u011f\64\3\2\2\2\u0120\u0121\t\7\2"+
		"\2\u0121\u0122\t\20\2\2\u0122\66\3\2\2\2\u0123\u0124\7C\2\2\u0124\u0125"+
		"\7N\2\2\u0125\u0126\7N\2\2\u01268\3\2\2\2\u0127\u0128\7X\2\2\u0128\u0129"+
		"\7G\2\2\u0129\u012a\7T\2\2\u012a\u012b\7U\2\2\u012b\u012c\7K\2\2\u012c"+
		"\u012d\7Q\2\2\u012d\u012e\7P\2\2\u012e\u012f\7U\2\2\u012f:\3\2\2\2\u0130"+
		"\u0131\7Q\2\2\u0131\u0132\7H\2\2\u0132<\3\2\2\2\u0133\u0134\7Y\2\2\u0134"+
		"\u0135\7K\2\2\u0135\u0136\7V\2\2\u0136\u0137\7J\2\2\u0137>\3\2\2\2\u0138"+
		"\u0139\7F\2\2\u0139\u013a\7G\2\2\u013a\u013b\7U\2\2\u013b\u013c\7E\2\2"+
		"\u013c\u013d\7G\2\2\u013d\u013e\7P\2\2\u013e\u013f\7F\2\2\u013f\u0140"+
		"\7C\2\2\u0140\u0141\7P\2\2\u0141\u0142\7V\2\2\u0142\u0143\7U\2\2\u0143"+
		"@\3\2\2\2\u0144\u0145\7O\2\2\u0145\u0146\7G\2\2\u0146\u0147\7T\2\2\u0147"+
		"\u0148\7I\2\2\u0148\u0149\7G\2\2\u0149\u014a\7F\2\2\u014aB\3\2\2\2\u014b"+
		"\u0155\7?\2\2\u014c\u014d\7#\2\2\u014d\u0155\7?\2\2\u014e\u0155\7@\2\2"+
		"\u014f\u0150\7@\2\2\u0150\u0155\7?\2\2\u0151\u0155\7>\2\2\u0152\u0153"+
		"\7>\2\2\u0153\u0155\7?\2\2\u0154\u014b\3\2\2\2\u0154\u014c\3\2\2\2\u0154"+
		"\u014e\3\2\2\2\u0154\u014f\3\2\2\2\u0154\u0151\3\2\2\2\u0154\u0152\3\2"+
		"\2\2\u0155D\3\2\2\2\u0156\u0157\t\21\2\2\u0157\u0158\t\17\2\2\u0158\u0159"+
		"\t\22\2\2\u0159\u015a\t\20\2\2\u015a\u015b\t\f\2\2\u015b\u015c\t\20\2"+
		"\2\u015cF\3\2\2\2\u015d\u0162\7)\2\2\u015e\u0161\5W,\2\u015f\u0161\n\23"+
		"\2\2\u0160\u015e\3\2\2\2\u0160\u015f\3\2\2\2\u0161\u0164\3\2\2\2\u0162"+
		"\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0165\3\2\2\2\u0164\u0162\3\2"+
		"\2\2\u0165\u0170\7)\2\2\u0166\u016b\7$\2\2\u0167\u016a\5W,\2\u0168\u016a"+
		"\n\24\2\2\u0169\u0167\3\2\2\2\u0169\u0168\3\2\2\2\u016a\u016d\3\2\2\2"+
		"\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016e\3\2\2\2\u016d\u016b"+
		"\3\2\2\2\u016e\u0170\7$\2\2\u016f\u015d\3\2\2\2\u016f\u0166\3\2\2\2\u0170"+
		"H\3\2\2\2\u0171\u0173\5S*\2\u0172\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174"+
		"\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0178\7/"+
		"\2\2\u0177\u0179\5S*\2\u0178\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u0178"+
		"\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017f\7/\2\2\u017d"+
		"\u0180\5O(\2\u017e\u0180\7a\2\2\u017f\u017d\3\2\2\2\u017f\u017e\3\2\2"+
		"\2\u0180\u0181\3\2\2\2\u0181\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0183"+
		"\3\2\2\2\u0183\u0186\7\60\2\2\u0184\u0187\5Q)\2\u0185\u0187\7/\2\2\u0186"+
		"\u0184\3\2\2\2\u0186\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0186\3\2"+
		"\2\2\u0188\u0189\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018b\7\60\2\2\u018b"+
		"\u018c\7x\2\2\u018c\u018e\3\2\2\2\u018d\u018f\5U+\2\u018e\u018d\3\2\2"+
		"\2\u018f\u0190\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0198"+
		"\3\2\2\2\u0192\u0194\7\60\2\2\u0193\u0195\5U+\2\u0194\u0193\3\2\2\2\u0195"+
		"\u0196\3\2\2\2\u0196\u0194\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0199\3\2"+
		"\2\2\u0198\u0192\3\2\2\2\u0198\u0199\3\2\2\2\u0199J\3\2\2\2\u019a\u019f"+
		"\t\7\2\2\u019b\u019e\5S*\2\u019c\u019e\7a\2\2\u019d\u019b\3\2\2\2\u019d"+
		"\u019c\3\2\2\2\u019e\u01a1\3\2\2\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2"+
		"\2\2\u01a0\u01aa\3\2\2\2\u01a1\u019f\3\2\2\2\u01a2\u01a6\5M\'\2\u01a3"+
		"\u01a5\5Q)\2\u01a4\u01a3\3\2\2\2\u01a5\u01a8\3\2\2\2\u01a6\u01a4\3\2\2"+
		"\2\u01a6\u01a7\3\2\2\2\u01a7\u01aa\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a9\u019a"+
		"\3\2\2\2\u01a9\u01a2\3\2\2\2\u01aaL\3\2\2\2\u01ab\u01ac\t\25\2\2\u01ac"+
		"N\3\2\2\2\u01ad\u01ae\t\26\2\2\u01aeP\3\2\2\2\u01af\u01b2\5S*\2\u01b0"+
		"\u01b2\7a\2\2\u01b1\u01af\3\2\2\2\u01b1\u01b0\3\2\2\2\u01b2R\3\2\2\2\u01b3"+
		"\u01b6\5O(\2\u01b4\u01b6\5U+\2\u01b5\u01b3\3\2\2\2\u01b5\u01b4\3\2\2\2"+
		"\u01b6T\3\2\2\2\u01b7\u01b8\4\62;\2\u01b8V\3\2\2\2\u01b9\u01ba\7^\2\2"+
		"\u01ba\u01bd\t\27\2\2\u01bb\u01bd\5Y-\2\u01bc\u01b9\3\2\2\2\u01bc\u01bb"+
		"\3\2\2\2\u01bdX\3\2\2\2\u01be\u01bf\7^\2\2\u01bf\u01c0\7w\2\2\u01c0\u01c1"+
		"\5[.\2\u01c1\u01c2\5[.\2\u01c2\u01c3\5[.\2\u01c3\u01c4\5[.\2\u01c4Z\3"+
		"\2\2\2\u01c5\u01c6\t\30\2\2\u01c6\\\3\2\2\2\u01c7\u01c8\7]\2\2\u01c8^"+
		"\3\2\2\2\u01c9\u01ca\7_\2\2\u01ca`\3\2\2\2#\2lv|\u0080\u00c8\u00cd\u00d0"+
		"\u00d5\u00db\u010f\u0154\u0160\u0162\u0169\u016b\u016f\u0174\u017a\u017f"+
		"\u0181\u0186\u0188\u0190\u0196\u0198\u019d\u019f\u01a6\u01a9\u01b1\u01b5"+
		"\u01bc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}