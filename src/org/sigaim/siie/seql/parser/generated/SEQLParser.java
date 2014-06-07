// Generated from SEQL.g4 by ANTLR 4.2.2
package org.sigaim.siie.seql.parser.generated;

import org.sigaim.siie.seql.parser.model.SEQLFromCondition.SEQLFromComponent;
import org.sigaim.siie.seql.parser.model.SEQLOperation;
import org.sigaim.siie.seql.parser.model.SEQLEvaluable;
import org.sigaim.siie.seql.parser.model.SEQLPrimitive;
import org.sigaim.siie.seql.parser.model.SEQLPath;



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
		WHERE=10, EHR=11, COMMA=12, FORWARD=13, BACKWARD=14, TOP=15, INTEGER=16, 
		BOOLEAN=17, AND=18, OR=19, XOR=20, NOT=21, AS=22, COMPARABLEOPERATOR=23, 
		EXISTS=24, STRING=25, ARCHETYPEID=26, IDENTIFIER=27, OPENBRACKET=28, CLOSEBRACKET=29;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "'('", "'/'", "';'", "WS", "NODEID", "'FROM'", "'CONTAINS'", 
		"'SELECT'", "'WHERE'", "EHR", "','", "FORWARD", "BACKWARD", "TOP", "INTEGER", 
		"BOOLEAN", "AND", "OR", "XOR", "NOT", "AS", "COMPARABLEOPERATOR", "EXISTS", 
		"STRING", "ARCHETYPEID", "IDENTIFIER", "'['", "']'"
	};
	public static final int
		RULE_query = 0, RULE_select = 1, RULE_top = 2, RULE_selectExpr = 3, RULE_identifiedPathSeq = 4, 
		RULE_selectVar = 5, RULE_asIdentifier = 6, RULE_identifiedPath = 7, RULE_objectPath = 8, 
		RULE_pathPart = 9, RULE_nodePredicate = 10, RULE_nodePredicateOr = 11, 
		RULE_nodePredicateAnd = 12, RULE_nodePredicateComparable = 13, RULE_from = 14, 
		RULE_containsExpr = 15, RULE_contains = 16, RULE_containExpressionBool = 17, 
		RULE_boolOp = 18, RULE_simpleClassExpr = 19, RULE_archetypedClassExpr = 20, 
		RULE_archetypePredicate = 21, RULE_where = 22, RULE_identifiedExpr = 23, 
		RULE_orOp = 24, RULE_identifiedExprAnd = 25, RULE_identifiedEquality = 26, 
		RULE_identifiedOperand = 27, RULE_operand = 28;
	public static final String[] ruleNames = {
		"query", "select", "top", "selectExpr", "identifiedPathSeq", "selectVar", 
		"asIdentifier", "identifiedPath", "objectPath", "pathPart", "nodePredicate", 
		"nodePredicateOr", "nodePredicateAnd", "nodePredicateComparable", "from", 
		"containsExpr", "contains", "containExpressionBool", "boolOp", "simpleClassExpr", 
		"archetypedClassExpr", "archetypePredicate", "where", "identifiedExpr", 
		"orOp", "identifiedExprAnd", "identifiedEquality", "identifiedOperand", 
		"operand"
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
			setState(58); select();
			setState(59); from();
			setState(61);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(60); where();
				}
			}

			setState(63); match(4);
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
			setState(65); match(SELECT);
			setState(67);
			_la = _input.LA(1);
			if (_la==TOP) {
				{
				setState(66); top();
				}
			}

			setState(69); selectExpr();
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
			setState(79);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(71); match(TOP);
				setState(72); match(INTEGER);
				setState(74);
				_la = _input.LA(1);
				if (_la==FORWARD) {
					{
					setState(73); match(FORWARD);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(76); match(TOP);
				setState(77); match(INTEGER);
				setState(78); match(BACKWARD);
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
			setState(81); identifiedPathSeq();
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
			setState(83); selectVar();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(84); match(COMMA);
				setState(85); selectVar();
				}
				}
				setState(90);
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
			setState(91); identifiedPath();
			setState(93);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(92); asIdentifier();
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
			setState(95); match(AS);
			setState(96); match(IDENTIFIER);
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
		enterRule(_localctx, 14, RULE_identifiedPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); match(IDENTIFIER);
			setState(100);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(99); nodePredicate();
				}
			}

			setState(104);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(102); match(3);
				setState(103); objectPath();
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
		enterRule(_localctx, 16, RULE_objectPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); pathPart();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==3) {
				{
				{
				setState(107); match(3);
				setState(108); pathPart();
				}
				}
				setState(113);
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
		enterRule(_localctx, 18, RULE_pathPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); match(IDENTIFIER);
			setState(116);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(115); nodePredicate();
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
		enterRule(_localctx, 20, RULE_nodePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); match(OPENBRACKET);
			setState(119); nodePredicateOr();
			setState(120); match(CLOSEBRACKET);
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
		enterRule(_localctx, 22, RULE_nodePredicateOr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); nodePredicateAnd();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(123); match(OR);
				setState(124); nodePredicateAnd();
				}
				}
				setState(129);
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
		enterRule(_localctx, 24, RULE_nodePredicateAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); nodePredicateComparable();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(131); match(AND);
				setState(132); nodePredicateComparable();
				}
				}
				setState(137);
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
		public TerminalNode ARCHETYPEID() { return getToken(SEQLParser.ARCHETYPEID, 0); }
		public TerminalNode NODEID() { return getToken(SEQLParser.NODEID, 0); }
		public TerminalNode COMMA() { return getToken(SEQLParser.COMMA, 0); }
		public TerminalNode STRING() { return getToken(SEQLParser.STRING, 0); }
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
		enterRule(_localctx, 26, RULE_nodePredicateComparable);
		int _la;
		try {
			setState(148);
			switch (_input.LA(1)) {
			case NODEID:
				enterOuterAlt(_localctx, 1);
				{
				setState(138); match(NODEID);
				setState(141);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(139); match(COMMA);
					{
					setState(140); match(STRING);
					}
					}
				}

				}
				break;
			case ARCHETYPEID:
				enterOuterAlt(_localctx, 2);
				{
				setState(143); match(ARCHETYPEID);
				setState(146);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(144); match(COMMA);
					{
					setState(145); match(STRING);
					}
					}
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

	public static class FromContext extends ParserRuleContext {
		public TerminalNode CONTAINS() { return getToken(SEQLParser.CONTAINS, 0); }
		public TerminalNode EHR() { return getToken(SEQLParser.EHR, 0); }
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
		enterRule(_localctx, 28, RULE_from);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150); match(FROM);
			setState(151); match(EHR);
			setState(152); match(CONTAINS);
			setState(153); containsExpr();
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
		enterRule(_localctx, 30, RULE_containsExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155); containExpressionBool();
			setState(159);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(156); boolOp();
				setState(157); containsExpr();
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
		enterRule(_localctx, 32, RULE_contains);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); simpleClassExpr();
			setState(164);
			_la = _input.LA(1);
			if (_la==CONTAINS) {
				{
				setState(162); match(CONTAINS);
				setState(163); containsExpr();
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
		enterRule(_localctx, 34, RULE_containExpressionBool);
		try {
			setState(171);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(166); contains();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(167); match(2);
				setState(168); containsExpr();
				setState(169); match(1);
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
		enterRule(_localctx, 36, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
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
		enterRule(_localctx, 38, RULE_simpleClassExpr);
		int _la;
		try {
			setState(180);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(175); match(IDENTIFIER);
				setState(177);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(176); match(IDENTIFIER);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(179); archetypedClassExpr();
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
		enterRule(_localctx, 40, RULE_archetypedClassExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); match(IDENTIFIER);
			setState(184);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(183); match(IDENTIFIER);
				}
			}

			setState(186); archetypePredicate();
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
		enterRule(_localctx, 42, RULE_archetypePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188); match(OPENBRACKET);
			setState(189); match(ARCHETYPEID);
			setState(190); match(CLOSEBRACKET);
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
		enterRule(_localctx, 44, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192); match(WHERE);
			setState(193); identifiedExpr();
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
		enterRule(_localctx, 46, RULE_identifiedExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195); identifiedExprAnd();
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR || _la==XOR) {
				{
				{
				setState(196); orOp();
				setState(197); identifiedExprAnd();
				}
				}
				setState(203);
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
		enterRule(_localctx, 48, RULE_orOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
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
		enterRule(_localctx, 50, RULE_identifiedExprAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); identifiedEquality();
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(207); match(AND);
				setState(208); identifiedEquality();
				}
				}
				setState(213);
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
		enterRule(_localctx, 52, RULE_identifiedEquality);
		try {
			setState(226);
			switch (_input.LA(1)) {
			case INTEGER:
			case BOOLEAN:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(214); identifiedOperand();
				setState(215); match(COMPARABLEOPERATOR);
				setState(216); identifiedOperand();
				}
				break;
			case EXISTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(218); match(EXISTS);
				setState(219); identifiedPath();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(220); match(2);
				setState(221); identifiedExpr();
				setState(222); match(1);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 4);
				{
				setState(224); match(NOT);
				setState(225); identifiedEquality();
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
		enterRule(_localctx, 54, RULE_identifiedOperand);
		try {
			setState(230);
			switch (_input.LA(1)) {
			case INTEGER:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(228); operand();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(229); identifiedPath();
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
		enterRule(_localctx, 56, RULE_operand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u00ed\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\5\2@\n"+
		"\2\3\2\3\2\3\3\3\3\5\3F\n\3\3\3\3\3\3\4\3\4\3\4\5\4M\n\4\3\4\3\4\3\4\5"+
		"\4R\n\4\3\5\3\5\3\6\3\6\3\6\7\6Y\n\6\f\6\16\6\\\13\6\3\7\3\7\5\7`\n\7"+
		"\3\b\3\b\3\b\3\t\3\t\5\tg\n\t\3\t\3\t\5\tk\n\t\3\n\3\n\3\n\7\np\n\n\f"+
		"\n\16\ns\13\n\3\13\3\13\5\13w\n\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\7\r\u0080"+
		"\n\r\f\r\16\r\u0083\13\r\3\16\3\16\3\16\7\16\u0088\n\16\f\16\16\16\u008b"+
		"\13\16\3\17\3\17\3\17\5\17\u0090\n\17\3\17\3\17\3\17\5\17\u0095\n\17\5"+
		"\17\u0097\n\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\5\21\u00a2"+
		"\n\21\3\22\3\22\3\22\5\22\u00a7\n\22\3\23\3\23\3\23\3\23\3\23\5\23\u00ae"+
		"\n\23\3\24\3\24\3\25\3\25\5\25\u00b4\n\25\3\25\5\25\u00b7\n\25\3\26\3"+
		"\26\5\26\u00bb\n\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\7\31\u00ca\n\31\f\31\16\31\u00cd\13\31\3\32\3\32\3\33"+
		"\3\33\3\33\7\33\u00d4\n\33\f\33\16\33\u00d7\13\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u00e5\n\34\3\35\3\35\5\35"+
		"\u00e9\n\35\3\36\3\36\3\36\2\2\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:\2\5\3\2\24\26\3\2\25\26\4\2\22\23\33\33\u00ea"+
		"\2<\3\2\2\2\4C\3\2\2\2\6Q\3\2\2\2\bS\3\2\2\2\nU\3\2\2\2\f]\3\2\2\2\16"+
		"a\3\2\2\2\20d\3\2\2\2\22l\3\2\2\2\24t\3\2\2\2\26x\3\2\2\2\30|\3\2\2\2"+
		"\32\u0084\3\2\2\2\34\u0096\3\2\2\2\36\u0098\3\2\2\2 \u009d\3\2\2\2\"\u00a3"+
		"\3\2\2\2$\u00ad\3\2\2\2&\u00af\3\2\2\2(\u00b6\3\2\2\2*\u00b8\3\2\2\2,"+
		"\u00be\3\2\2\2.\u00c2\3\2\2\2\60\u00c5\3\2\2\2\62\u00ce\3\2\2\2\64\u00d0"+
		"\3\2\2\2\66\u00e4\3\2\2\28\u00e8\3\2\2\2:\u00ea\3\2\2\2<=\5\4\3\2=?\5"+
		"\36\20\2>@\5.\30\2?>\3\2\2\2?@\3\2\2\2@A\3\2\2\2AB\7\6\2\2B\3\3\2\2\2"+
		"CE\7\13\2\2DF\5\6\4\2ED\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\5\b\5\2H\5\3\2\2"+
		"\2IJ\7\21\2\2JL\7\22\2\2KM\7\17\2\2LK\3\2\2\2LM\3\2\2\2MR\3\2\2\2NO\7"+
		"\21\2\2OP\7\22\2\2PR\7\20\2\2QI\3\2\2\2QN\3\2\2\2R\7\3\2\2\2ST\5\n\6\2"+
		"T\t\3\2\2\2UZ\5\f\7\2VW\7\16\2\2WY\5\f\7\2XV\3\2\2\2Y\\\3\2\2\2ZX\3\2"+
		"\2\2Z[\3\2\2\2[\13\3\2\2\2\\Z\3\2\2\2]_\5\20\t\2^`\5\16\b\2_^\3\2\2\2"+
		"_`\3\2\2\2`\r\3\2\2\2ab\7\30\2\2bc\7\35\2\2c\17\3\2\2\2df\7\35\2\2eg\5"+
		"\26\f\2fe\3\2\2\2fg\3\2\2\2gj\3\2\2\2hi\7\5\2\2ik\5\22\n\2jh\3\2\2\2j"+
		"k\3\2\2\2k\21\3\2\2\2lq\5\24\13\2mn\7\5\2\2np\5\24\13\2om\3\2\2\2ps\3"+
		"\2\2\2qo\3\2\2\2qr\3\2\2\2r\23\3\2\2\2sq\3\2\2\2tv\7\35\2\2uw\5\26\f\2"+
		"vu\3\2\2\2vw\3\2\2\2w\25\3\2\2\2xy\7\36\2\2yz\5\30\r\2z{\7\37\2\2{\27"+
		"\3\2\2\2|\u0081\5\32\16\2}~\7\25\2\2~\u0080\5\32\16\2\177}\3\2\2\2\u0080"+
		"\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\31\3\2\2\2"+
		"\u0083\u0081\3\2\2\2\u0084\u0089\5\34\17\2\u0085\u0086\7\24\2\2\u0086"+
		"\u0088\5\34\17\2\u0087\u0085\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3"+
		"\2\2\2\u0089\u008a\3\2\2\2\u008a\33\3\2\2\2\u008b\u0089\3\2\2\2\u008c"+
		"\u008f\7\b\2\2\u008d\u008e\7\16\2\2\u008e\u0090\7\33\2\2\u008f\u008d\3"+
		"\2\2\2\u008f\u0090\3\2\2\2\u0090\u0097\3\2\2\2\u0091\u0094\7\34\2\2\u0092"+
		"\u0093\7\16\2\2\u0093\u0095\7\33\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3"+
		"\2\2\2\u0095\u0097\3\2\2\2\u0096\u008c\3\2\2\2\u0096\u0091\3\2\2\2\u0097"+
		"\35\3\2\2\2\u0098\u0099\7\t\2\2\u0099\u009a\7\r\2\2\u009a\u009b\7\n\2"+
		"\2\u009b\u009c\5 \21\2\u009c\37\3\2\2\2\u009d\u00a1\5$\23\2\u009e\u009f"+
		"\5&\24\2\u009f\u00a0\5 \21\2\u00a0\u00a2\3\2\2\2\u00a1\u009e\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2!\3\2\2\2\u00a3\u00a6\5(\25\2\u00a4\u00a5\7\n\2\2"+
		"\u00a5\u00a7\5 \21\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7#\3"+
		"\2\2\2\u00a8\u00ae\5\"\22\2\u00a9\u00aa\7\4\2\2\u00aa\u00ab\5 \21\2\u00ab"+
		"\u00ac\7\3\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00a8\3\2\2\2\u00ad\u00a9\3\2"+
		"\2\2\u00ae%\3\2\2\2\u00af\u00b0\t\2\2\2\u00b0\'\3\2\2\2\u00b1\u00b3\7"+
		"\35\2\2\u00b2\u00b4\7\35\2\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b7\3\2\2\2\u00b5\u00b7\5*\26\2\u00b6\u00b1\3\2\2\2\u00b6\u00b5\3\2"+
		"\2\2\u00b7)\3\2\2\2\u00b8\u00ba\7\35\2\2\u00b9\u00bb\7\35\2\2\u00ba\u00b9"+
		"\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\5,\27\2\u00bd"+
		"+\3\2\2\2\u00be\u00bf\7\36\2\2\u00bf\u00c0\7\34\2\2\u00c0\u00c1\7\37\2"+
		"\2\u00c1-\3\2\2\2\u00c2\u00c3\7\f\2\2\u00c3\u00c4\5\60\31\2\u00c4/\3\2"+
		"\2\2\u00c5\u00cb\5\64\33\2\u00c6\u00c7\5\62\32\2\u00c7\u00c8\5\64\33\2"+
		"\u00c8\u00ca\3\2\2\2\u00c9\u00c6\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9"+
		"\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\61\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce"+
		"\u00cf\t\3\2\2\u00cf\63\3\2\2\2\u00d0\u00d5\5\66\34\2\u00d1\u00d2\7\24"+
		"\2\2\u00d2\u00d4\5\66\34\2\u00d3\u00d1\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\65\3\2\2\2\u00d7\u00d5\3\2\2"+
		"\2\u00d8\u00d9\58\35\2\u00d9\u00da\7\31\2\2\u00da\u00db\58\35\2\u00db"+
		"\u00e5\3\2\2\2\u00dc\u00dd\7\32\2\2\u00dd\u00e5\5\20\t\2\u00de\u00df\7"+
		"\4\2\2\u00df\u00e0\5\60\31\2\u00e0\u00e1\7\3\2\2\u00e1\u00e5\3\2\2\2\u00e2"+
		"\u00e3\7\27\2\2\u00e3\u00e5\5\66\34\2\u00e4\u00d8\3\2\2\2\u00e4\u00dc"+
		"\3\2\2\2\u00e4\u00de\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\67\3\2\2\2\u00e6"+
		"\u00e9\5:\36\2\u00e7\u00e9\5\20\t\2\u00e8\u00e6\3\2\2\2\u00e8\u00e7\3"+
		"\2\2\2\u00e99\3\2\2\2\u00ea\u00eb\t\4\2\2\u00eb;\3\2\2\2\33?ELQZ_fjqv"+
		"\u0081\u0089\u008f\u0094\u0096\u00a1\u00a6\u00ad\u00b3\u00b6\u00ba\u00cb"+
		"\u00d5\u00e4\u00e8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}