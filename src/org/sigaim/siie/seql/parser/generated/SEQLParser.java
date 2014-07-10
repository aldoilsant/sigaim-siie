// Generated from SEQL.g4 by ANTLR 4.2
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
		VERSIONS=25, OF=26, WITH=27, DESCENDANTS=28, COMPARABLEOPERATOR=29, EXISTS=30, 
		STRING=31, ARCHETYPEID=32, IDENTIFIER=33, OPENBRACKET=34, CLOSEBRACKET=35;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "'('", "'/'", "';'", "WS", "NODEID", "'FROM'", "'CONTAINS'", 
		"'SELECT'", "'WHERE'", "'EHR'", "'SYSTEM'", "','", "FORWARD", "BACKWARD", 
		"TOP", "INTEGER", "BOOLEAN", "AND", "OR", "XOR", "NOT", "AS", "'ALL'", 
		"'VERSIONS'", "'OF'", "'WITH'", "'DESCENDANTS'", "COMPARABLEOPERATOR", 
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

			setState(75); selectExpr();
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
			setState(85);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(77); match(TOP);
				setState(78); match(INTEGER);
				setState(80);
				_la = _input.LA(1);
				if (_la==FORWARD) {
					{
					setState(79); match(FORWARD);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(82); match(TOP);
				setState(83); match(INTEGER);
				setState(84); match(BACKWARD);
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
			setState(87); identifiedPathSeq();
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
			setState(89); selectVar();
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(90); match(COMMA);
				setState(91); selectVar();
				}
				}
				setState(96);
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
			setState(97); identifiedPath();
			setState(99);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(98); asIdentifier();
				}
			}

			setState(102);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(101); withDescendants();
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
			setState(104); match(AS);
			setState(105); match(IDENTIFIER);
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
			setState(107); match(WITH);
			setState(108); match(DESCENDANTS);
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
			setState(110); match(IDENTIFIER);
			setState(112);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(111); nodePredicate();
				}
			}

			setState(116);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(114); match(3);
				setState(115); objectPath();
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
			setState(118); pathPart();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==3) {
				{
				{
				setState(119); match(3);
				setState(120); pathPart();
				}
				}
				setState(125);
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
			setState(126); match(IDENTIFIER);
			setState(128);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(127); nodePredicate();
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
			setState(130); match(OPENBRACKET);
			setState(131); nodePredicateOr();
			setState(132); match(CLOSEBRACKET);
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
			setState(134); nodePredicateAnd();
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(135); match(OR);
				setState(136); nodePredicateAnd();
				}
				}
				setState(141);
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
			setState(142); nodePredicateComparable();
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(143); match(AND);
				setState(144); nodePredicateComparable();
				}
				}
				setState(149);
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
			setState(168);
			switch (_input.LA(1)) {
			case NODEID:
				enterOuterAlt(_localctx, 1);
				{
				setState(150); match(NODEID);
				setState(153);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(151); match(COMMA);
					{
					setState(152); match(STRING);
					}
					}
				}

				}
				break;
			case ARCHETYPEID:
				enterOuterAlt(_localctx, 2);
				{
				setState(155); match(ARCHETYPEID);
				setState(158);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(156); match(COMMA);
					{
					setState(157); match(STRING);
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
				setState(162);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(160); match(COMMA);
					{
					setState(161); match(STRING);
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
				setState(164); predicateOperand();
				{
				setState(165); match(COMPARABLEOPERATOR);
				setState(166); predicateOperand();
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
			setState(172);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(170); objectPath();
				}
				break;
			case INTEGER:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(171); operand();
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
			setState(196);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(174); match(FROM);
				setState(175); match(EHR);
				setState(176); match(SYSTEM);
				setState(177); match(IDENTIFIER);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(178); match(FROM);
				setState(179); match(EHR);
				setState(180); match(SYSTEM);
				setState(182);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(181); match(IDENTIFIER);
					}
				}

				setState(184); match(CONTAINS);
				setState(185); containsExpr();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(186); match(FROM);
				setState(187); match(EHR);
				setState(188); match(IDENTIFIER);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(189); match(FROM);
				setState(190); match(EHR);
				setState(192);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(191); match(IDENTIFIER);
					}
				}

				setState(194); match(CONTAINS);
				setState(195); containsExpr();
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
			setState(198); containExpressionBool();
			setState(202);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(199); boolOp();
				setState(200); containsExpr();
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
			setState(204); simpleClassExpr();
			setState(207);
			_la = _input.LA(1);
			if (_la==CONTAINS) {
				{
				setState(205); match(CONTAINS);
				setState(206); containsExpr();
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
			setState(209); match(ALL);
			setState(210); match(VERSIONS);
			setState(211); match(OF);
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
			setState(218);
			switch (_input.LA(1)) {
			case ALL:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(213); contains();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214); match(2);
				setState(215); containsExpr();
				setState(216); match(1);
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
			setState(220);
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
			setState(230);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				_la = _input.LA(1);
				if (_la==ALL) {
					{
					setState(222); allVersions();
					}
				}

				setState(225); match(IDENTIFIER);
				setState(227);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(226); match(IDENTIFIER);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229); archetypedClassExpr();
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
			setState(233);
			_la = _input.LA(1);
			if (_la==ALL) {
				{
				setState(232); allVersions();
				}
			}

			setState(235); match(IDENTIFIER);
			setState(237);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(236); match(IDENTIFIER);
				}
			}

			setState(239); archetypePredicate();
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
			setState(241); match(OPENBRACKET);
			setState(242); match(ARCHETYPEID);
			setState(243); match(CLOSEBRACKET);
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
			setState(245); match(WHERE);
			setState(246); identifiedExpr();
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
			setState(248); identifiedExprAnd();
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR || _la==XOR) {
				{
				{
				setState(249); orOp();
				setState(250); identifiedExprAnd();
				}
				}
				setState(256);
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
			setState(257);
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
			setState(259); identifiedEquality();
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(260); match(AND);
				setState(261); identifiedEquality();
				}
				}
				setState(266);
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
			setState(279);
			switch (_input.LA(1)) {
			case INTEGER:
			case BOOLEAN:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(267); identifiedOperand();
				setState(268); match(COMPARABLEOPERATOR);
				setState(269); identifiedOperand();
				}
				break;
			case EXISTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(271); match(EXISTS);
				setState(272); identifiedPath();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(273); match(2);
				setState(274); identifiedExpr();
				setState(275); match(1);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 4);
				{
				setState(277); match(NOT);
				setState(278); identifiedEquality();
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
			setState(283);
			switch (_input.LA(1)) {
			case INTEGER:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(281); operand();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(282); identifiedPath();
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
			setState(285);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3%\u0122\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\2\5\2F\n\2\3\2\3\2\3\3\3\3\5\3L\n\3\3\3\3\3\3\4\3\4\3\4"+
		"\5\4S\n\4\3\4\3\4\3\4\5\4X\n\4\3\5\3\5\3\6\3\6\3\6\7\6_\n\6\f\6\16\6b"+
		"\13\6\3\7\3\7\5\7f\n\7\3\7\5\7i\n\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\5"+
		"\ns\n\n\3\n\3\n\5\nw\n\n\3\13\3\13\3\13\7\13|\n\13\f\13\16\13\177\13\13"+
		"\3\f\3\f\5\f\u0083\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\7\16\u008c\n\16"+
		"\f\16\16\16\u008f\13\16\3\17\3\17\3\17\7\17\u0094\n\17\f\17\16\17\u0097"+
		"\13\17\3\20\3\20\3\20\5\20\u009c\n\20\3\20\3\20\3\20\5\20\u00a1\n\20\3"+
		"\20\3\20\5\20\u00a5\n\20\3\20\3\20\3\20\3\20\5\20\u00ab\n\20\3\21\3\21"+
		"\5\21\u00af\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00b9\n"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c3\n\22\3\22\3\22"+
		"\5\22\u00c7\n\22\3\23\3\23\3\23\3\23\5\23\u00cd\n\23\3\24\3\24\3\24\5"+
		"\24\u00d2\n\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\5\26\u00dd"+
		"\n\26\3\27\3\27\3\30\5\30\u00e2\n\30\3\30\3\30\5\30\u00e6\n\30\3\30\5"+
		"\30\u00e9\n\30\3\31\5\31\u00ec\n\31\3\31\3\31\5\31\u00f0\n\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u00ff\n\34"+
		"\f\34\16\34\u0102\13\34\3\35\3\35\3\36\3\36\3\36\7\36\u0109\n\36\f\36"+
		"\16\36\u010c\13\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\5\37\u011a\n\37\3 \3 \5 \u011e\n \3!\3!\3!\2\2\"\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\5\3\2\25\27\3"+
		"\2\26\27\4\2\23\24!!\u0128\2B\3\2\2\2\4I\3\2\2\2\6W\3\2\2\2\bY\3\2\2\2"+
		"\n[\3\2\2\2\fc\3\2\2\2\16j\3\2\2\2\20m\3\2\2\2\22p\3\2\2\2\24x\3\2\2\2"+
		"\26\u0080\3\2\2\2\30\u0084\3\2\2\2\32\u0088\3\2\2\2\34\u0090\3\2\2\2\36"+
		"\u00aa\3\2\2\2 \u00ae\3\2\2\2\"\u00c6\3\2\2\2$\u00c8\3\2\2\2&\u00ce\3"+
		"\2\2\2(\u00d3\3\2\2\2*\u00dc\3\2\2\2,\u00de\3\2\2\2.\u00e8\3\2\2\2\60"+
		"\u00eb\3\2\2\2\62\u00f3\3\2\2\2\64\u00f7\3\2\2\2\66\u00fa\3\2\2\28\u0103"+
		"\3\2\2\2:\u0105\3\2\2\2<\u0119\3\2\2\2>\u011d\3\2\2\2@\u011f\3\2\2\2B"+
		"C\5\4\3\2CE\5\"\22\2DF\5\64\33\2ED\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\7\6\2"+
		"\2H\3\3\2\2\2IK\7\13\2\2JL\5\6\4\2KJ\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\5\b"+
		"\5\2N\5\3\2\2\2OP\7\22\2\2PR\7\23\2\2QS\7\20\2\2RQ\3\2\2\2RS\3\2\2\2S"+
		"X\3\2\2\2TU\7\22\2\2UV\7\23\2\2VX\7\21\2\2WO\3\2\2\2WT\3\2\2\2X\7\3\2"+
		"\2\2YZ\5\n\6\2Z\t\3\2\2\2[`\5\f\7\2\\]\7\17\2\2]_\5\f\7\2^\\\3\2\2\2_"+
		"b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\13\3\2\2\2b`\3\2\2\2ce\5\22\n\2df\5\16"+
		"\b\2ed\3\2\2\2ef\3\2\2\2fh\3\2\2\2gi\5\20\t\2hg\3\2\2\2hi\3\2\2\2i\r\3"+
		"\2\2\2jk\7\31\2\2kl\7#\2\2l\17\3\2\2\2mn\7\35\2\2no\7\36\2\2o\21\3\2\2"+
		"\2pr\7#\2\2qs\5\30\r\2rq\3\2\2\2rs\3\2\2\2sv\3\2\2\2tu\7\5\2\2uw\5\24"+
		"\13\2vt\3\2\2\2vw\3\2\2\2w\23\3\2\2\2x}\5\26\f\2yz\7\5\2\2z|\5\26\f\2"+
		"{y\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\25\3\2\2\2\177}\3\2\2\2\u0080"+
		"\u0082\7#\2\2\u0081\u0083\5\30\r\2\u0082\u0081\3\2\2\2\u0082\u0083\3\2"+
		"\2\2\u0083\27\3\2\2\2\u0084\u0085\7$\2\2\u0085\u0086\5\32\16\2\u0086\u0087"+
		"\7%\2\2\u0087\31\3\2\2\2\u0088\u008d\5\34\17\2\u0089\u008a\7\26\2\2\u008a"+
		"\u008c\5\34\17\2\u008b\u0089\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3"+
		"\2\2\2\u008d\u008e\3\2\2\2\u008e\33\3\2\2\2\u008f\u008d\3\2\2\2\u0090"+
		"\u0095\5\36\20\2\u0091\u0092\7\25\2\2\u0092\u0094\5\36\20\2\u0093\u0091"+
		"\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\35\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009b\7\b\2\2\u0099\u009a\7\17\2"+
		"\2\u009a\u009c\7!\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u00ab"+
		"\3\2\2\2\u009d\u00a0\7\"\2\2\u009e\u009f\7\17\2\2\u009f\u00a1\7!\2\2\u00a0"+
		"\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00ab\3\2\2\2\u00a2\u00a3\7\17"+
		"\2\2\u00a3\u00a5\7!\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00ab\3\2\2\2\u00a6\u00a7\5 \21\2\u00a7\u00a8\7\37\2\2\u00a8\u00a9\5"+
		" \21\2\u00a9\u00ab\3\2\2\2\u00aa\u0098\3\2\2\2\u00aa\u009d\3\2\2\2\u00aa"+
		"\u00a4\3\2\2\2\u00aa\u00a6\3\2\2\2\u00ab\37\3\2\2\2\u00ac\u00af\5\24\13"+
		"\2\u00ad\u00af\5@!\2\u00ae\u00ac\3\2\2\2\u00ae\u00ad\3\2\2\2\u00af!\3"+
		"\2\2\2\u00b0\u00b1\7\t\2\2\u00b1\u00b2\7\r\2\2\u00b2\u00b3\7\16\2\2\u00b3"+
		"\u00c7\7#\2\2\u00b4\u00b5\7\t\2\2\u00b5\u00b6\7\r\2\2\u00b6\u00b8\7\16"+
		"\2\2\u00b7\u00b9\7#\2\2\u00b8\u00b7\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba\u00bb\7\n\2\2\u00bb\u00c7\5$\23\2\u00bc\u00bd\7\t"+
		"\2\2\u00bd\u00be\7\r\2\2\u00be\u00c7\7#\2\2\u00bf\u00c0\7\t\2\2\u00c0"+
		"\u00c2\7\r\2\2\u00c1\u00c3\7#\2\2\u00c2\u00c1\3\2\2\2\u00c2\u00c3\3\2"+
		"\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\7\n\2\2\u00c5\u00c7\5$\23\2\u00c6"+
		"\u00b0\3\2\2\2\u00c6\u00b4\3\2\2\2\u00c6\u00bc\3\2\2\2\u00c6\u00bf\3\2"+
		"\2\2\u00c7#\3\2\2\2\u00c8\u00cc\5*\26\2\u00c9\u00ca\5,\27\2\u00ca\u00cb"+
		"\5$\23\2\u00cb\u00cd\3\2\2\2\u00cc\u00c9\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"%\3\2\2\2\u00ce\u00d1\5.\30\2\u00cf\u00d0\7\n\2\2\u00d0\u00d2\5$\23\2"+
		"\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\'\3\2\2\2\u00d3\u00d4\7"+
		"\32\2\2\u00d4\u00d5\7\33\2\2\u00d5\u00d6\7\34\2\2\u00d6)\3\2\2\2\u00d7"+
		"\u00dd\5&\24\2\u00d8\u00d9\7\4\2\2\u00d9\u00da\5$\23\2\u00da\u00db\7\3"+
		"\2\2\u00db\u00dd\3\2\2\2\u00dc\u00d7\3\2\2\2\u00dc\u00d8\3\2\2\2\u00dd"+
		"+\3\2\2\2\u00de\u00df\t\2\2\2\u00df-\3\2\2\2\u00e0\u00e2\5(\25\2\u00e1"+
		"\u00e0\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\7#"+
		"\2\2\u00e4\u00e6\7#\2\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e9\3\2\2\2\u00e7\u00e9\5\60\31\2\u00e8\u00e1\3\2\2\2\u00e8\u00e7\3"+
		"\2\2\2\u00e9/\3\2\2\2\u00ea\u00ec\5(\25\2\u00eb\u00ea\3\2\2\2\u00eb\u00ec"+
		"\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\7#\2\2\u00ee\u00f0\7#\2\2\u00ef"+
		"\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f2\5\62"+
		"\32\2\u00f2\61\3\2\2\2\u00f3\u00f4\7$\2\2\u00f4\u00f5\7\"\2\2\u00f5\u00f6"+
		"\7%\2\2\u00f6\63\3\2\2\2\u00f7\u00f8\7\f\2\2\u00f8\u00f9\5\66\34\2\u00f9"+
		"\65\3\2\2\2\u00fa\u0100\5:\36\2\u00fb\u00fc\58\35\2\u00fc\u00fd\5:\36"+
		"\2\u00fd\u00ff\3\2\2\2\u00fe\u00fb\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe"+
		"\3\2\2\2\u0100\u0101\3\2\2\2\u0101\67\3\2\2\2\u0102\u0100\3\2\2\2\u0103"+
		"\u0104\t\3\2\2\u01049\3\2\2\2\u0105\u010a\5<\37\2\u0106\u0107\7\25\2\2"+
		"\u0107\u0109\5<\37\2\u0108\u0106\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108"+
		"\3\2\2\2\u010a\u010b\3\2\2\2\u010b;\3\2\2\2\u010c\u010a\3\2\2\2\u010d"+
		"\u010e\5> \2\u010e\u010f\7\37\2\2\u010f\u0110\5> \2\u0110\u011a\3\2\2"+
		"\2\u0111\u0112\7 \2\2\u0112\u011a\5\22\n\2\u0113\u0114\7\4\2\2\u0114\u0115"+
		"\5\66\34\2\u0115\u0116\7\3\2\2\u0116\u011a\3\2\2\2\u0117\u0118\7\30\2"+
		"\2\u0118\u011a\5<\37\2\u0119\u010d\3\2\2\2\u0119\u0111\3\2\2\2\u0119\u0113"+
		"\3\2\2\2\u0119\u0117\3\2\2\2\u011a=\3\2\2\2\u011b\u011e\5@!\2\u011c\u011e"+
		"\5\22\n\2\u011d\u011b\3\2\2\2\u011d\u011c\3\2\2\2\u011e?\3\2\2\2\u011f"+
		"\u0120\t\4\2\2\u0120A\3\2\2\2#EKRW`ehrv}\u0082\u008d\u0095\u009b\u00a0"+
		"\u00a4\u00aa\u00ae\u00b8\u00c2\u00c6\u00cc\u00d1\u00dc\u00e1\u00e5\u00e8"+
		"\u00eb\u00ef\u0100\u010a\u0119\u011d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}