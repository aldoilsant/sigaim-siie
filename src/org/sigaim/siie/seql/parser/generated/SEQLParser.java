// Generated from org/sigaim/siie/seql/parser/generated/SEQL.g4 by ANTLR 4.2

package org.sigaim.siie.seql.parser.generated;
import org.sigaim.siie.seql.model.SEQLFromComponent;
import org.sigaim.siie.seql.model.SEQLOperation;
import org.sigaim.siie.seql.model.SEQLEvaluable;
import org.sigaim.siie.seql.model.SEQLPrimitive;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.seql.model.SEQLPathPredicate;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SEQLParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, WS=5, NODEID=6, FROM=7, CONTAINS=8, SELECT=9, 
		WHERE=10, EHR=11, SYSTEM=12, COMMA=13, FORWARD=14, BACKWARD=15, TOP=16, 
		INTEGER=17, BOOLEAN=18, AND=19, OR=20, XOR=21, NOT=22, AS=23, ALL=24, 
		VERSIONS=25, OF=26, WITH=27, DESCENDANTS=28, MERGED=29, COMPARABLEOPERATOR=30, 
		EXISTS=31, STRING=32, ARCHETYPEID=33, IDENTIFIER=34, OPENBRACKET=35, CLOSEBRACKET=36;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "'('", "'/'", "';'", "WS", "NODEID", "'FROM'", "'CONTAINS'", 
		"'SELECT'", "'WHERE'", "'EHR'", "'SYSTEM'", "','", "FORWARD", "BACKWARD", 
		"TOP", "INTEGER", "BOOLEAN", "AND", "OR", "XOR", "NOT", "AS", "'ALL'", 
		"'VERSIONS'", "'OF'", "'WITH'", "'DESCENDANTS'", "'MERGED'", "COMPARABLEOPERATOR", 
		"EXISTS", "STRING", "ARCHETYPEID", "IDENTIFIER", "'['", "']'"
	};
	public static final int
		RULE_query = 0, RULE_select = 1, RULE_top = 2, RULE_selectExpr = 3, RULE_identifiedPathSeq = 4, 
		RULE_selectVar = 5, RULE_asIdentifier = 6, RULE_withDescendants = 7, RULE_identifiedPath = 8, 
		RULE_objectPath = 9, RULE_pathPart = 10, RULE_nodePredicate = 11, RULE_nodePredicateOr = 12, 
		RULE_nodePredicateAnd = 13, RULE_nodePredicateComparable = 14, RULE_predicateOperand = 15, 
		RULE_from = 16, RULE_containsExpr = 17, RULE_contains = 18, RULE_allVersions = 19, 
		RULE_containExpressionBool = 20, RULE_boolOp = 21, RULE_simpleClassExpr = 22, 
		RULE_archetypedClassExpr = 23, RULE_archetypePredicate = 24, RULE_where = 25, 
		RULE_identifiedExpr = 26, RULE_orOp = 27, RULE_identifiedExprAnd = 28, 
		RULE_identifiedEquality = 29, RULE_identifiedOperand = 30, RULE_operand = 31;
	public static final String[] ruleNames = {
		"query", "select", "top", "selectExpr", "identifiedPathSeq", "selectVar", 
		"asIdentifier", "withDescendants", "identifiedPath", "objectPath", "pathPart", 
		"nodePredicate", "nodePredicateOr", "nodePredicateAnd", "nodePredicateComparable", 
		"predicateOperand", "from", "containsExpr", "contains", "allVersions", 
		"containExpressionBool", "boolOp", "simpleClassExpr", "archetypedClassExpr", 
		"archetypePredicate", "where", "identifiedExpr", "orOp", "identifiedExprAnd", 
		"identifiedEquality", "identifiedOperand", "operand"
	};

	@Override
	public String getGrammarFileName() { return "SEQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SEQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public FromContext from() {
			return getRuleContext(FromContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); select();
			setState(65); from();
			setState(67);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(66); where();
				}
			}

			setState(69); match(4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectContext extends ParserRuleContext {
		public SelectExprContext selectExpr() {
			return getRuleContext(SelectExprContext.class,0);
		}
		public TopContext top() {
			return getRuleContext(TopContext.class,0);
		}
		public TerminalNode SELECT() { return getToken(SEQLParser.SELECT, 0); }
		public TerminalNode MERGED() { return getToken(SEQLParser.MERGED, 0); }
		public SelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterSelect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitSelect(this);
		}
	}

	public final SelectContext select() throws RecognitionException {
		SelectContext _localctx = new SelectContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_select);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71); match(SELECT);
			setState(73);
			_la = _input.LA(1);
			if (_la==TOP) {
				{
				setState(72); top();
				}
			}

			setState(76);
			_la = _input.LA(1);
			if (_la==MERGED) {
				{
				setState(75); match(MERGED);
				}
			}

			setState(78); selectExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TopContext extends ParserRuleContext {
		public TerminalNode FORWARD() { return getToken(SEQLParser.FORWARD, 0); }
		public TerminalNode BACKWARD() { return getToken(SEQLParser.BACKWARD, 0); }
		public TerminalNode TOP() { return getToken(SEQLParser.TOP, 0); }
		public TerminalNode INTEGER() { return getToken(SEQLParser.INTEGER, 0); }
		public TopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_top; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterTop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitTop(this);
		}
	}

	public final TopContext top() throws RecognitionException {
		TopContext _localctx = new TopContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_top);
		int _la;
		try {
			setState(88);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80); match(TOP);
				setState(81); match(INTEGER);
				setState(83);
				_la = _input.LA(1);
				if (_la==FORWARD) {
					{
					setState(82); match(FORWARD);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85); match(TOP);
				setState(86); match(INTEGER);
				setState(87); match(BACKWARD);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectExprContext extends ParserRuleContext {
		public IdentifiedPathSeqContext identifiedPathSeq() {
			return getRuleContext(IdentifiedPathSeqContext.class,0);
		}
		public SelectExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterSelectExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitSelectExpr(this);
		}
	}

	public final SelectExprContext selectExpr() throws RecognitionException {
		SelectExprContext _localctx = new SelectExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_selectExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); identifiedPathSeq();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifiedPathSeqContext extends ParserRuleContext {
		public List<SelectVarContext> selectVar() {
			return getRuleContexts(SelectVarContext.class);
		}
		public SelectVarContext selectVar(int i) {
			return getRuleContext(SelectVarContext.class,i);
		}
		public IdentifiedPathSeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiedPathSeq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterIdentifiedPathSeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitIdentifiedPathSeq(this);
		}
	}

	public final IdentifiedPathSeqContext identifiedPathSeq() throws RecognitionException {
		IdentifiedPathSeqContext _localctx = new IdentifiedPathSeqContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_identifiedPathSeq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); selectVar();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(93); match(COMMA);
				setState(94); selectVar();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectVarContext extends ParserRuleContext {
		public IdentifiedPathContext identifiedPath() {
			return getRuleContext(IdentifiedPathContext.class,0);
		}
		public AsIdentifierContext asIdentifier() {
			return getRuleContext(AsIdentifierContext.class,0);
		}
		public WithDescendantsContext withDescendants() {
			return getRuleContext(WithDescendantsContext.class,0);
		}
		public SelectVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterSelectVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitSelectVar(this);
		}
	}

	public final SelectVarContext selectVar() throws RecognitionException {
		SelectVarContext _localctx = new SelectVarContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_selectVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); identifiedPath();
			setState(102);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(101); asIdentifier();
				}
			}

			setState(105);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(104); withDescendants();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AsIdentifierContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(SEQLParser.AS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SEQLParser.IDENTIFIER, 0); }
		public AsIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterAsIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitAsIdentifier(this);
		}
	}

	public final AsIdentifierContext asIdentifier() throws RecognitionException {
		AsIdentifierContext _localctx = new AsIdentifierContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_asIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); match(AS);
			setState(108); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WithDescendantsContext extends ParserRuleContext {
		public TerminalNode DESCENDANTS() { return getToken(SEQLParser.DESCENDANTS, 0); }
		public TerminalNode WITH() { return getToken(SEQLParser.WITH, 0); }
		public WithDescendantsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withDescendants; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterWithDescendants(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitWithDescendants(this);
		}
	}

	public final WithDescendantsContext withDescendants() throws RecognitionException {
		WithDescendantsContext _localctx = new WithDescendantsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_withDescendants);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(WITH);
			setState(111); match(DESCENDANTS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifiedPathContext extends ParserRuleContext {
		public SEQLPath path;
		public TerminalNode IDENTIFIER() { return getToken(SEQLParser.IDENTIFIER, 0); }
		public ObjectPathContext objectPath() {
			return getRuleContext(ObjectPathContext.class,0);
		}
		public NodePredicateContext nodePredicate() {
			return getRuleContext(NodePredicateContext.class,0);
		}
		public IdentifiedPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiedPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterIdentifiedPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitIdentifiedPath(this);
		}
	}

	public final IdentifiedPathContext identifiedPath() throws RecognitionException {
		IdentifiedPathContext _localctx = new IdentifiedPathContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_identifiedPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113); match(IDENTIFIER);
			setState(115);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(114); nodePredicate();
				}
			}

			setState(119);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(117); match(3);
				setState(118); objectPath();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectPathContext extends ParserRuleContext {
		public SEQLPath path;
		public PathPartContext pathPart(int i) {
			return getRuleContext(PathPartContext.class,i);
		}
		public List<PathPartContext> pathPart() {
			return getRuleContexts(PathPartContext.class);
		}
		public ObjectPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterObjectPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitObjectPath(this);
		}
	}

	public final ObjectPathContext objectPath() throws RecognitionException {
		ObjectPathContext _localctx = new ObjectPathContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_objectPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121); pathPart();
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==3) {
				{
				{
				setState(122); match(3);
				setState(123); pathPart();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathPartContext extends ParserRuleContext {
		public SEQLPathComponent pathComponent;
		public TerminalNode IDENTIFIER() { return getToken(SEQLParser.IDENTIFIER, 0); }
		public NodePredicateContext nodePredicate() {
			return getRuleContext(NodePredicateContext.class,0);
		}
		public PathPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterPathPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitPathPart(this);
		}
	}

	public final PathPartContext pathPart() throws RecognitionException {
		PathPartContext _localctx = new PathPartContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pathPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); match(IDENTIFIER);
			setState(131);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(130); nodePredicate();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodePredicateContext extends ParserRuleContext {
		public SEQLPathPredicate pathPredicate;
		public TerminalNode OPENBRACKET() { return getToken(SEQLParser.OPENBRACKET, 0); }
		public TerminalNode CLOSEBRACKET() { return getToken(SEQLParser.CLOSEBRACKET, 0); }
		public NodePredicateOrContext nodePredicateOr() {
			return getRuleContext(NodePredicateOrContext.class,0);
		}
		public NodePredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodePredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterNodePredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitNodePredicate(this);
		}
	}

	public final NodePredicateContext nodePredicate() throws RecognitionException {
		NodePredicateContext _localctx = new NodePredicateContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_nodePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133); match(OPENBRACKET);
			setState(134); nodePredicateOr();
			setState(135); match(CLOSEBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodePredicateOrContext extends ParserRuleContext {
		public SEQLEvaluable evaluable;
		public NodePredicateAndContext nodePredicateAnd(int i) {
			return getRuleContext(NodePredicateAndContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(SEQLParser.OR); }
		public List<NodePredicateAndContext> nodePredicateAnd() {
			return getRuleContexts(NodePredicateAndContext.class);
		}
		public TerminalNode OR(int i) {
			return getToken(SEQLParser.OR, i);
		}
		public NodePredicateOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodePredicateOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterNodePredicateOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitNodePredicateOr(this);
		}
	}

	public final NodePredicateOrContext nodePredicateOr() throws RecognitionException {
		NodePredicateOrContext _localctx = new NodePredicateOrContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_nodePredicateOr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137); nodePredicateAnd();
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(138); match(OR);
				setState(139); nodePredicateAnd();
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodePredicateAndContext extends ParserRuleContext {
		public SEQLEvaluable evaluable;
		public List<TerminalNode> AND() { return getTokens(SEQLParser.AND); }
		public List<NodePredicateComparableContext> nodePredicateComparable() {
			return getRuleContexts(NodePredicateComparableContext.class);
		}
		public NodePredicateComparableContext nodePredicateComparable(int i) {
			return getRuleContext(NodePredicateComparableContext.class,i);
		}
		public TerminalNode AND(int i) {
			return getToken(SEQLParser.AND, i);
		}
		public NodePredicateAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodePredicateAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterNodePredicateAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitNodePredicateAnd(this);
		}
	}

	public final NodePredicateAndContext nodePredicateAnd() throws RecognitionException {
		NodePredicateAndContext _localctx = new NodePredicateAndContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_nodePredicateAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); nodePredicateComparable();
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(146); match(AND);
				setState(147); nodePredicateComparable();
				}
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodePredicateComparableContext extends ParserRuleContext {
		public SEQLEvaluable evaluable;
		public TerminalNode COMPARABLEOPERATOR() { return getToken(SEQLParser.COMPARABLEOPERATOR, 0); }
		public TerminalNode ARCHETYPEID() { return getToken(SEQLParser.ARCHETYPEID, 0); }
		public TerminalNode NODEID() { return getToken(SEQLParser.NODEID, 0); }
		public List<PredicateOperandContext> predicateOperand() {
			return getRuleContexts(PredicateOperandContext.class);
		}
		public TerminalNode COMMA() { return getToken(SEQLParser.COMMA, 0); }
		public TerminalNode STRING() { return getToken(SEQLParser.STRING, 0); }
		public PredicateOperandContext predicateOperand(int i) {
			return getRuleContext(PredicateOperandContext.class,i);
		}
		public NodePredicateComparableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodePredicateComparable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterNodePredicateComparable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitNodePredicateComparable(this);
		}
	}

	public final NodePredicateComparableContext nodePredicateComparable() throws RecognitionException {
		NodePredicateComparableContext _localctx = new NodePredicateComparableContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_nodePredicateComparable);
		int _la;
		try {
			setState(171);
			switch (_input.LA(1)) {
			case NODEID:
				enterOuterAlt(_localctx, 1);
				{
				setState(153); match(NODEID);
				setState(156);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(154); match(COMMA);
					{
					setState(155); match(STRING);
					}
					}
				}

				}
				break;
			case ARCHETYPEID:
				enterOuterAlt(_localctx, 2);
				{
				setState(158); match(ARCHETYPEID);
				setState(161);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(159); match(COMMA);
					{
					setState(160); match(STRING);
					}
					}
				}

				}
				break;
			case COMMA:
			case AND:
			case OR:
			case CLOSEBRACKET:
				enterOuterAlt(_localctx, 3);
				{
				setState(165);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(163); match(COMMA);
					{
					setState(164); match(STRING);
					}
					}
				}

				}
				break;
			case INTEGER:
			case BOOLEAN:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(167); predicateOperand();
				{
				setState(168); match(COMPARABLEOPERATOR);
				setState(169); predicateOperand();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateOperandContext extends ParserRuleContext {
		public SEQLEvaluable evaluable;
		public ObjectPathContext objectPath() {
			return getRuleContext(ObjectPathContext.class,0);
		}
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public PredicateOperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicateOperand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterPredicateOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitPredicateOperand(this);
		}
	}

	public final PredicateOperandContext predicateOperand() throws RecognitionException {
		PredicateOperandContext _localctx = new PredicateOperandContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_predicateOperand);
		try {
			setState(175);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(173); objectPath();
				}
				break;
			case INTEGER:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(174); operand();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FromContext extends ParserRuleContext {
		public TerminalNode CONTAINS() { return getToken(SEQLParser.CONTAINS, 0); }
		public TerminalNode SYSTEM() { return getToken(SEQLParser.SYSTEM, 0); }
		public TerminalNode EHR() { return getToken(SEQLParser.EHR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SEQLParser.IDENTIFIER, 0); }
		public ContainsExprContext containsExpr() {
			return getRuleContext(ContainsExprContext.class,0);
		}
		public TerminalNode FROM() { return getToken(SEQLParser.FROM, 0); }
		public FromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterFrom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitFrom(this);
		}
	}

	public final FromContext from() throws RecognitionException {
		FromContext _localctx = new FromContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_from);
		int _la;
		try {
			setState(199);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(177); match(FROM);
				setState(178); match(EHR);
				setState(179); match(SYSTEM);
				setState(180); match(IDENTIFIER);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(181); match(FROM);
				setState(182); match(EHR);
				setState(183); match(SYSTEM);
				setState(185);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(184); match(IDENTIFIER);
					}
				}

				setState(187); match(CONTAINS);
				setState(188); containsExpr();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(189); match(FROM);
				setState(190); match(EHR);
				setState(191); match(IDENTIFIER);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(192); match(FROM);
				setState(193); match(EHR);
				setState(195);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(194); match(IDENTIFIER);
					}
				}

				setState(197); match(CONTAINS);
				setState(198); containsExpr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContainsExprContext extends ParserRuleContext {
		public SEQLOperation operation;
		public ContainExpressionBoolContext containExpressionBool() {
			return getRuleContext(ContainExpressionBoolContext.class,0);
		}
		public ContainsExprContext containsExpr() {
			return getRuleContext(ContainsExprContext.class,0);
		}
		public BoolOpContext boolOp() {
			return getRuleContext(BoolOpContext.class,0);
		}
		public ContainsExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_containsExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterContainsExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitContainsExpr(this);
		}
	}

	public final ContainsExprContext containsExpr() throws RecognitionException {
		ContainsExprContext _localctx = new ContainsExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_containsExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201); containExpressionBool();
			setState(205);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(202); boolOp();
				setState(203); containsExpr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContainsContext extends ParserRuleContext {
		public SEQLOperation containsOperation;
		public TerminalNode CONTAINS() { return getToken(SEQLParser.CONTAINS, 0); }
		public SimpleClassExprContext simpleClassExpr() {
			return getRuleContext(SimpleClassExprContext.class,0);
		}
		public ContainsExprContext containsExpr() {
			return getRuleContext(ContainsExprContext.class,0);
		}
		public ContainsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contains; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterContains(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitContains(this);
		}
	}

	public final ContainsContext contains() throws RecognitionException {
		ContainsContext _localctx = new ContainsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_contains);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207); simpleClassExpr();
			setState(210);
			_la = _input.LA(1);
			if (_la==CONTAINS) {
				{
				setState(208); match(CONTAINS);
				setState(209); containsExpr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllVersionsContext extends ParserRuleContext {
		public TerminalNode ALL() { return getToken(SEQLParser.ALL, 0); }
		public TerminalNode VERSIONS() { return getToken(SEQLParser.VERSIONS, 0); }
		public TerminalNode OF() { return getToken(SEQLParser.OF, 0); }
		public AllVersionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allVersions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterAllVersions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitAllVersions(this);
		}
	}

	public final AllVersionsContext allVersions() throws RecognitionException {
		AllVersionsContext _localctx = new AllVersionsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_allVersions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212); match(ALL);
			setState(213); match(VERSIONS);
			setState(214); match(OF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContainExpressionBoolContext extends ParserRuleContext {
		public SEQLOperation operation;
		public ContainsContext contains() {
			return getRuleContext(ContainsContext.class,0);
		}
		public ContainsExprContext containsExpr() {
			return getRuleContext(ContainsExprContext.class,0);
		}
		public ContainExpressionBoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_containExpressionBool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterContainExpressionBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitContainExpressionBool(this);
		}
	}

	public final ContainExpressionBoolContext containExpressionBool() throws RecognitionException {
		ContainExpressionBoolContext _localctx = new ContainExpressionBoolContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_containExpressionBool);
		try {
			setState(221);
			switch (_input.LA(1)) {
			case ALL:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(216); contains();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(217); match(2);
				setState(218); containsExpr();
				setState(219); match(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolOpContext extends ParserRuleContext {
		public TerminalNode XOR() { return getToken(SEQLParser.XOR, 0); }
		public TerminalNode AND() { return getToken(SEQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(SEQLParser.OR, 0); }
		public BoolOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterBoolOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitBoolOp(this);
		}
	}

	public final BoolOpContext boolOp() throws RecognitionException {
		BoolOpContext _localctx = new BoolOpContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleClassExprContext extends ParserRuleContext {
		public SEQLFromComponent component;
		public ArchetypedClassExprContext archetypedClassExpr() {
			return getRuleContext(ArchetypedClassExprContext.class,0);
		}
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SEQLParser.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(SEQLParser.IDENTIFIER); }
		public AllVersionsContext allVersions() {
			return getRuleContext(AllVersionsContext.class,0);
		}
		public SimpleClassExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleClassExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterSimpleClassExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitSimpleClassExpr(this);
		}
	}

	public final SimpleClassExprContext simpleClassExpr() throws RecognitionException {
		SimpleClassExprContext _localctx = new SimpleClassExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_simpleClassExpr);
		int _la;
		try {
			setState(233);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				_la = _input.LA(1);
				if (_la==ALL) {
					{
					setState(225); allVersions();
					}
				}

				setState(228); match(IDENTIFIER);
				setState(230);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(229); match(IDENTIFIER);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232); archetypedClassExpr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArchetypedClassExprContext extends ParserRuleContext {
		public ArchetypePredicateContext archetypePredicate() {
			return getRuleContext(ArchetypePredicateContext.class,0);
		}
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SEQLParser.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(SEQLParser.IDENTIFIER); }
		public AllVersionsContext allVersions() {
			return getRuleContext(AllVersionsContext.class,0);
		}
		public ArchetypedClassExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_archetypedClassExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterArchetypedClassExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitArchetypedClassExpr(this);
		}
	}

	public final ArchetypedClassExprContext archetypedClassExpr() throws RecognitionException {
		ArchetypedClassExprContext _localctx = new ArchetypedClassExprContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_archetypedClassExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			_la = _input.LA(1);
			if (_la==ALL) {
				{
				setState(235); allVersions();
				}
			}

			setState(238); match(IDENTIFIER);
			setState(240);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(239); match(IDENTIFIER);
				}
			}

			setState(242); archetypePredicate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArchetypePredicateContext extends ParserRuleContext {
		public TerminalNode OPENBRACKET() { return getToken(SEQLParser.OPENBRACKET, 0); }
		public TerminalNode ARCHETYPEID() { return getToken(SEQLParser.ARCHETYPEID, 0); }
		public TerminalNode CLOSEBRACKET() { return getToken(SEQLParser.CLOSEBRACKET, 0); }
		public ArchetypePredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_archetypePredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterArchetypePredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitArchetypePredicate(this);
		}
	}

	public final ArchetypePredicateContext archetypePredicate() throws RecognitionException {
		ArchetypePredicateContext _localctx = new ArchetypePredicateContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_archetypePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244); match(OPENBRACKET);
			setState(245); match(ARCHETYPEID);
			setState(246); match(CLOSEBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(SEQLParser.WHERE, 0); }
		public IdentifiedExprContext identifiedExpr() {
			return getRuleContext(IdentifiedExprContext.class,0);
		}
		public WhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterWhere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitWhere(this);
		}
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248); match(WHERE);
			setState(249); identifiedExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifiedExprContext extends ParserRuleContext {
		public SEQLOperation operation;
		public List<OrOpContext> orOp() {
			return getRuleContexts(OrOpContext.class);
		}
		public List<IdentifiedExprAndContext> identifiedExprAnd() {
			return getRuleContexts(IdentifiedExprAndContext.class);
		}
		public IdentifiedExprAndContext identifiedExprAnd(int i) {
			return getRuleContext(IdentifiedExprAndContext.class,i);
		}
		public OrOpContext orOp(int i) {
			return getRuleContext(OrOpContext.class,i);
		}
		public IdentifiedExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiedExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterIdentifiedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitIdentifiedExpr(this);
		}
	}

	public final IdentifiedExprContext identifiedExpr() throws RecognitionException {
		IdentifiedExprContext _localctx = new IdentifiedExprContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_identifiedExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251); identifiedExprAnd();
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR || _la==XOR) {
				{
				{
				setState(252); orOp();
				setState(253); identifiedExprAnd();
				}
				}
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrOpContext extends ParserRuleContext {
		public TerminalNode XOR() { return getToken(SEQLParser.XOR, 0); }
		public TerminalNode OR() { return getToken(SEQLParser.OR, 0); }
		public OrOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterOrOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitOrOp(this);
		}
	}

	public final OrOpContext orOp() throws RecognitionException {
		OrOpContext _localctx = new OrOpContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_orOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			_la = _input.LA(1);
			if ( !(_la==OR || _la==XOR) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifiedExprAndContext extends ParserRuleContext {
		public SEQLOperation operation;
		public List<IdentifiedEqualityContext> identifiedEquality() {
			return getRuleContexts(IdentifiedEqualityContext.class);
		}
		public List<TerminalNode> AND() { return getTokens(SEQLParser.AND); }
		public IdentifiedEqualityContext identifiedEquality(int i) {
			return getRuleContext(IdentifiedEqualityContext.class,i);
		}
		public TerminalNode AND(int i) {
			return getToken(SEQLParser.AND, i);
		}
		public IdentifiedExprAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiedExprAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterIdentifiedExprAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitIdentifiedExprAnd(this);
		}
	}

	public final IdentifiedExprAndContext identifiedExprAnd() throws RecognitionException {
		IdentifiedExprAndContext _localctx = new IdentifiedExprAndContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_identifiedExprAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262); identifiedEquality();
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(263); match(AND);
				setState(264); identifiedEquality();
				}
				}
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifiedEqualityContext extends ParserRuleContext {
		public SEQLOperation operation;
		public IdentifiedPathContext identifiedPath() {
			return getRuleContext(IdentifiedPathContext.class,0);
		}
		public TerminalNode EXISTS() { return getToken(SEQLParser.EXISTS, 0); }
		public IdentifiedEqualityContext identifiedEquality() {
			return getRuleContext(IdentifiedEqualityContext.class,0);
		}
		public TerminalNode COMPARABLEOPERATOR() { return getToken(SEQLParser.COMPARABLEOPERATOR, 0); }
		public IdentifiedOperandContext identifiedOperand(int i) {
			return getRuleContext(IdentifiedOperandContext.class,i);
		}
		public List<IdentifiedOperandContext> identifiedOperand() {
			return getRuleContexts(IdentifiedOperandContext.class);
		}
		public TerminalNode NOT() { return getToken(SEQLParser.NOT, 0); }
		public IdentifiedExprContext identifiedExpr() {
			return getRuleContext(IdentifiedExprContext.class,0);
		}
		public IdentifiedEqualityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiedEquality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterIdentifiedEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitIdentifiedEquality(this);
		}
	}

	public final IdentifiedEqualityContext identifiedEquality() throws RecognitionException {
		IdentifiedEqualityContext _localctx = new IdentifiedEqualityContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_identifiedEquality);
		try {
			setState(282);
			switch (_input.LA(1)) {
			case INTEGER:
			case BOOLEAN:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(270); identifiedOperand();
				setState(271); match(COMPARABLEOPERATOR);
				setState(272); identifiedOperand();
				}
				break;
			case EXISTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(274); match(EXISTS);
				setState(275); identifiedPath();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(276); match(2);
				setState(277); identifiedExpr();
				setState(278); match(1);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 4);
				{
				setState(280); match(NOT);
				setState(281); identifiedEquality();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifiedOperandContext extends ParserRuleContext {
		public SEQLEvaluable evaluable;
		public IdentifiedPathContext identifiedPath() {
			return getRuleContext(IdentifiedPathContext.class,0);
		}
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public IdentifiedOperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiedOperand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterIdentifiedOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitIdentifiedOperand(this);
		}
	}

	public final IdentifiedOperandContext identifiedOperand() throws RecognitionException {
		IdentifiedOperandContext _localctx = new IdentifiedOperandContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_identifiedOperand);
		try {
			setState(286);
			switch (_input.LA(1)) {
			case INTEGER:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(284); operand();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(285); identifiedPath();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperandContext extends ParserRuleContext {
		public SEQLPrimitive primitive;
		public TerminalNode BOOLEAN() { return getToken(SEQLParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(SEQLParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(SEQLParser.STRING, 0); }
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitOperand(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_operand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << BOOLEAN) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u0125\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\2\5\2F\n\2\3\2\3\2\3\3\3\3\5\3L\n\3\3\3\5\3O\n\3\3\3\3\3"+
		"\3\4\3\4\3\4\5\4V\n\4\3\4\3\4\3\4\5\4[\n\4\3\5\3\5\3\6\3\6\3\6\7\6b\n"+
		"\6\f\6\16\6e\13\6\3\7\3\7\5\7i\n\7\3\7\5\7l\n\7\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\n\3\n\5\nv\n\n\3\n\3\n\5\nz\n\n\3\13\3\13\3\13\7\13\177\n\13\f\13"+
		"\16\13\u0082\13\13\3\f\3\f\5\f\u0086\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\7\16\u008f\n\16\f\16\16\16\u0092\13\16\3\17\3\17\3\17\7\17\u0097\n\17"+
		"\f\17\16\17\u009a\13\17\3\20\3\20\3\20\5\20\u009f\n\20\3\20\3\20\3\20"+
		"\5\20\u00a4\n\20\3\20\3\20\5\20\u00a8\n\20\3\20\3\20\3\20\3\20\5\20\u00ae"+
		"\n\20\3\21\3\21\5\21\u00b2\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u00bc\n\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c6\n"+
		"\22\3\22\3\22\5\22\u00ca\n\22\3\23\3\23\3\23\3\23\5\23\u00d0\n\23\3\24"+
		"\3\24\3\24\5\24\u00d5\n\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\5\26\u00e0\n\26\3\27\3\27\3\30\5\30\u00e5\n\30\3\30\3\30\5\30\u00e9\n"+
		"\30\3\30\5\30\u00ec\n\30\3\31\5\31\u00ef\n\31\3\31\3\31\5\31\u00f3\n\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\7\34"+
		"\u0102\n\34\f\34\16\34\u0105\13\34\3\35\3\35\3\36\3\36\3\36\7\36\u010c"+
		"\n\36\f\36\16\36\u010f\13\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\5\37\u011d\n\37\3 \3 \5 \u0121\n \3!\3!\3!\2\2\"\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\5\3"+
		"\2\25\27\3\2\26\27\4\2\23\24\"\"\u012c\2B\3\2\2\2\4I\3\2\2\2\6Z\3\2\2"+
		"\2\b\\\3\2\2\2\n^\3\2\2\2\ff\3\2\2\2\16m\3\2\2\2\20p\3\2\2\2\22s\3\2\2"+
		"\2\24{\3\2\2\2\26\u0083\3\2\2\2\30\u0087\3\2\2\2\32\u008b\3\2\2\2\34\u0093"+
		"\3\2\2\2\36\u00ad\3\2\2\2 \u00b1\3\2\2\2\"\u00c9\3\2\2\2$\u00cb\3\2\2"+
		"\2&\u00d1\3\2\2\2(\u00d6\3\2\2\2*\u00df\3\2\2\2,\u00e1\3\2\2\2.\u00eb"+
		"\3\2\2\2\60\u00ee\3\2\2\2\62\u00f6\3\2\2\2\64\u00fa\3\2\2\2\66\u00fd\3"+
		"\2\2\28\u0106\3\2\2\2:\u0108\3\2\2\2<\u011c\3\2\2\2>\u0120\3\2\2\2@\u0122"+
		"\3\2\2\2BC\5\4\3\2CE\5\"\22\2DF\5\64\33\2ED\3\2\2\2EF\3\2\2\2FG\3\2\2"+
		"\2GH\7\6\2\2H\3\3\2\2\2IK\7\13\2\2JL\5\6\4\2KJ\3\2\2\2KL\3\2\2\2LN\3\2"+
		"\2\2MO\7\37\2\2NM\3\2\2\2NO\3\2\2\2OP\3\2\2\2PQ\5\b\5\2Q\5\3\2\2\2RS\7"+
		"\22\2\2SU\7\23\2\2TV\7\20\2\2UT\3\2\2\2UV\3\2\2\2V[\3\2\2\2WX\7\22\2\2"+
		"XY\7\23\2\2Y[\7\21\2\2ZR\3\2\2\2ZW\3\2\2\2[\7\3\2\2\2\\]\5\n\6\2]\t\3"+
		"\2\2\2^c\5\f\7\2_`\7\17\2\2`b\5\f\7\2a_\3\2\2\2be\3\2\2\2ca\3\2\2\2cd"+
		"\3\2\2\2d\13\3\2\2\2ec\3\2\2\2fh\5\22\n\2gi\5\16\b\2hg\3\2\2\2hi\3\2\2"+
		"\2ik\3\2\2\2jl\5\20\t\2kj\3\2\2\2kl\3\2\2\2l\r\3\2\2\2mn\7\31\2\2no\7"+
		"$\2\2o\17\3\2\2\2pq\7\35\2\2qr\7\36\2\2r\21\3\2\2\2su\7$\2\2tv\5\30\r"+
		"\2ut\3\2\2\2uv\3\2\2\2vy\3\2\2\2wx\7\5\2\2xz\5\24\13\2yw\3\2\2\2yz\3\2"+
		"\2\2z\23\3\2\2\2{\u0080\5\26\f\2|}\7\5\2\2}\177\5\26\f\2~|\3\2\2\2\177"+
		"\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\25\3\2\2\2\u0082"+
		"\u0080\3\2\2\2\u0083\u0085\7$\2\2\u0084\u0086\5\30\r\2\u0085\u0084\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0086\27\3\2\2\2\u0087\u0088\7%\2\2\u0088\u0089"+
		"\5\32\16\2\u0089\u008a\7&\2\2\u008a\31\3\2\2\2\u008b\u0090\5\34\17\2\u008c"+
		"\u008d\7\26\2\2\u008d\u008f\5\34\17\2\u008e\u008c\3\2\2\2\u008f\u0092"+
		"\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\33\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0093\u0098\5\36\20\2\u0094\u0095\7\25\2\2\u0095\u0097"+
		"\5\36\20\2\u0096\u0094\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2"+
		"\u0098\u0099\3\2\2\2\u0099\35\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009e"+
		"\7\b\2\2\u009c\u009d\7\17\2\2\u009d\u009f\7\"\2\2\u009e\u009c\3\2\2\2"+
		"\u009e\u009f\3\2\2\2\u009f\u00ae\3\2\2\2\u00a0\u00a3\7#\2\2\u00a1\u00a2"+
		"\7\17\2\2\u00a2\u00a4\7\"\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2"+
		"\u00a4\u00ae\3\2\2\2\u00a5\u00a6\7\17\2\2\u00a6\u00a8\7\"\2\2\u00a7\u00a5"+
		"\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00ae\3\2\2\2\u00a9\u00aa\5 \21\2\u00aa"+
		"\u00ab\7 \2\2\u00ab\u00ac\5 \21\2\u00ac\u00ae\3\2\2\2\u00ad\u009b\3\2"+
		"\2\2\u00ad\u00a0\3\2\2\2\u00ad\u00a7\3\2\2\2\u00ad\u00a9\3\2\2\2\u00ae"+
		"\37\3\2\2\2\u00af\u00b2\5\24\13\2\u00b0\u00b2\5@!\2\u00b1\u00af\3\2\2"+
		"\2\u00b1\u00b0\3\2\2\2\u00b2!\3\2\2\2\u00b3\u00b4\7\t\2\2\u00b4\u00b5"+
		"\7\r\2\2\u00b5\u00b6\7\16\2\2\u00b6\u00ca\7$\2\2\u00b7\u00b8\7\t\2\2\u00b8"+
		"\u00b9\7\r\2\2\u00b9\u00bb\7\16\2\2\u00ba\u00bc\7$\2\2\u00bb\u00ba\3\2"+
		"\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\7\n\2\2\u00be"+
		"\u00ca\5$\23\2\u00bf\u00c0\7\t\2\2\u00c0\u00c1\7\r\2\2\u00c1\u00ca\7$"+
		"\2\2\u00c2\u00c3\7\t\2\2\u00c3\u00c5\7\r\2\2\u00c4\u00c6\7$\2\2\u00c5"+
		"\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\7\n"+
		"\2\2\u00c8\u00ca\5$\23\2\u00c9\u00b3\3\2\2\2\u00c9\u00b7\3\2\2\2\u00c9"+
		"\u00bf\3\2\2\2\u00c9\u00c2\3\2\2\2\u00ca#\3\2\2\2\u00cb\u00cf\5*\26\2"+
		"\u00cc\u00cd\5,\27\2\u00cd\u00ce\5$\23\2\u00ce\u00d0\3\2\2\2\u00cf\u00cc"+
		"\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0%\3\2\2\2\u00d1\u00d4\5.\30\2\u00d2"+
		"\u00d3\7\n\2\2\u00d3\u00d5\5$\23\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2"+
		"\2\2\u00d5\'\3\2\2\2\u00d6\u00d7\7\32\2\2\u00d7\u00d8\7\33\2\2\u00d8\u00d9"+
		"\7\34\2\2\u00d9)\3\2\2\2\u00da\u00e0\5&\24\2\u00db\u00dc\7\4\2\2\u00dc"+
		"\u00dd\5$\23\2\u00dd\u00de\7\3\2\2\u00de\u00e0\3\2\2\2\u00df\u00da\3\2"+
		"\2\2\u00df\u00db\3\2\2\2\u00e0+\3\2\2\2\u00e1\u00e2\t\2\2\2\u00e2-\3\2"+
		"\2\2\u00e3\u00e5\5(\25\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00e8\7$\2\2\u00e7\u00e9\7$\2\2\u00e8\u00e7\3\2\2"+
		"\2\u00e8\u00e9\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00ec\5\60\31\2\u00eb"+
		"\u00e4\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec/\3\2\2\2\u00ed\u00ef\5(\25\2"+
		"\u00ee\u00ed\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f2"+
		"\7$\2\2\u00f1\u00f3\7$\2\2\u00f2\u00f1\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f5\5\62\32\2\u00f5\61\3\2\2\2\u00f6\u00f7\7%\2"+
		"\2\u00f7\u00f8\7#\2\2\u00f8\u00f9\7&\2\2\u00f9\63\3\2\2\2\u00fa\u00fb"+
		"\7\f\2\2\u00fb\u00fc\5\66\34\2\u00fc\65\3\2\2\2\u00fd\u0103\5:\36\2\u00fe"+
		"\u00ff\58\35\2\u00ff\u0100\5:\36\2\u0100\u0102\3\2\2\2\u0101\u00fe\3\2"+
		"\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104"+
		"\67\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0107\t\3\2\2\u01079\3\2\2\2\u0108"+
		"\u010d\5<\37\2\u0109\u010a\7\25\2\2\u010a\u010c\5<\37\2\u010b\u0109\3"+
		"\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e"+
		";\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0111\5> \2\u0111\u0112\7 \2\2\u0112"+
		"\u0113\5> \2\u0113\u011d\3\2\2\2\u0114\u0115\7!\2\2\u0115\u011d\5\22\n"+
		"\2\u0116\u0117\7\4\2\2\u0117\u0118\5\66\34\2\u0118\u0119\7\3\2\2\u0119"+
		"\u011d\3\2\2\2\u011a\u011b\7\30\2\2\u011b\u011d\5<\37\2\u011c\u0110\3"+
		"\2\2\2\u011c\u0114\3\2\2\2\u011c\u0116\3\2\2\2\u011c\u011a\3\2\2\2\u011d"+
		"=\3\2\2\2\u011e\u0121\5@!\2\u011f\u0121\5\22\n\2\u0120\u011e\3\2\2\2\u0120"+
		"\u011f\3\2\2\2\u0121?\3\2\2\2\u0122\u0123\t\4\2\2\u0123A\3\2\2\2$EKNU"+
		"Zchkuy\u0080\u0085\u0090\u0098\u009e\u00a3\u00a7\u00ad\u00b1\u00bb\u00c5"+
		"\u00c9\u00cf\u00d4\u00df\u00e4\u00e8\u00eb\u00ee\u00f2\u0103\u010d\u011c"+
		"\u0120";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}