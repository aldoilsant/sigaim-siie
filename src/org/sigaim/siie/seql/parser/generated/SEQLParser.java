// Generated from SEQL.g4 by ANTLR 4.2
package org.sigaim.siie.seql.parser.generated;

import org.sigaim.siie.seql.parser.model.SEQLFromComponent;
import org.sigaim.siie.seql.parser.model.SEQLOperation;
import org.sigaim.siie.seql.parser.model.SEQLEvaluable;
import org.sigaim.siie.seql.parser.model.SEQLPrimitive;
import org.sigaim.siie.seql.parser.model.SEQLPath;
import org.sigaim.siie.seql.parser.model.SEQLPathComponent;
import org.sigaim.siie.seql.parser.model.SEQLPathPredicate;

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
		RULE_nodePredicateAnd = 12, RULE_nodePredicateComparable = 13, RULE_predicateOperand = 14, 
		RULE_from = 15, RULE_containsExpr = 16, RULE_contains = 17, RULE_containExpressionBool = 18, 
		RULE_boolOp = 19, RULE_simpleClassExpr = 20, RULE_archetypedClassExpr = 21, 
		RULE_archetypePredicate = 22, RULE_where = 23, RULE_identifiedExpr = 24, 
		RULE_orOp = 25, RULE_identifiedExprAnd = 26, RULE_identifiedEquality = 27, 
		RULE_identifiedOperand = 28, RULE_operand = 29;
	public static final String[] ruleNames = {
		"query", "select", "top", "selectExpr", "identifiedPathSeq", "selectVar", 
		"asIdentifier", "identifiedPath", "objectPath", "pathPart", "nodePredicate", 
		"nodePredicateOr", "nodePredicateAnd", "nodePredicateComparable", "predicateOperand", 
		"from", "containsExpr", "contains", "containExpressionBool", "boolOp", 
		"simpleClassExpr", "archetypedClassExpr", "archetypePredicate", "where", 
		"identifiedExpr", "orOp", "identifiedExprAnd", "identifiedEquality", "identifiedOperand", 
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
			setState(60); select();
			setState(61); from();
			setState(63);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(62); where();
				}
			}

			setState(65); match(4);
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
			setState(67); match(SELECT);
			setState(69);
			_la = _input.LA(1);
			if (_la==TOP) {
				{
				setState(68); top();
				}
			}

			setState(71); selectExpr();
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
			setState(81);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(73); match(TOP);
				setState(74); match(INTEGER);
				setState(76);
				_la = _input.LA(1);
				if (_la==FORWARD) {
					{
					setState(75); match(FORWARD);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78); match(TOP);
				setState(79); match(INTEGER);
				setState(80); match(BACKWARD);
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
			setState(83); identifiedPathSeq();
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
			setState(85); selectVar();
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(86); match(COMMA);
				setState(87); selectVar();
				}
				}
				setState(92);
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
			setState(93); identifiedPath();
			setState(95);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(94); asIdentifier();
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
			setState(97); match(AS);
			setState(98); match(IDENTIFIER);
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
			setState(100); match(IDENTIFIER);
			setState(102);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(101); nodePredicate();
				}
			}

			setState(106);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(104); match(3);
				setState(105); objectPath();
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
		enterRule(_localctx, 16, RULE_objectPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); pathPart();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==3) {
				{
				{
				setState(109); match(3);
				setState(110); pathPart();
				}
				}
				setState(115);
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
		enterRule(_localctx, 18, RULE_pathPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); match(IDENTIFIER);
			setState(118);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(117); nodePredicate();
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
		enterRule(_localctx, 20, RULE_nodePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(OPENBRACKET);
			setState(121); nodePredicateOr();
			setState(122); match(CLOSEBRACKET);
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
		enterRule(_localctx, 22, RULE_nodePredicateOr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); nodePredicateAnd();
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(125); match(OR);
				setState(126); nodePredicateAnd();
				}
				}
				setState(131);
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
		enterRule(_localctx, 24, RULE_nodePredicateAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132); nodePredicateComparable();
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(133); match(AND);
				setState(134); nodePredicateComparable();
				}
				}
				setState(139);
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
		enterRule(_localctx, 26, RULE_nodePredicateComparable);
		int _la;
		try {
			setState(158);
			switch (_input.LA(1)) {
			case NODEID:
				enterOuterAlt(_localctx, 1);
				{
				setState(140); match(NODEID);
				setState(143);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(141); match(COMMA);
					{
					setState(142); match(STRING);
					}
					}
				}

				}
				break;
			case ARCHETYPEID:
				enterOuterAlt(_localctx, 2);
				{
				setState(145); match(ARCHETYPEID);
				setState(148);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(146); match(COMMA);
					{
					setState(147); match(STRING);
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
				setState(152);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(150); match(COMMA);
					{
					setState(151); match(STRING);
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
				setState(154); predicateOperand();
				{
				setState(155); match(COMPARABLEOPERATOR);
				setState(156); predicateOperand();
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
		enterRule(_localctx, 28, RULE_predicateOperand);
		try {
			setState(162);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(160); objectPath();
				}
				break;
			case INTEGER:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(161); operand();
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
		enterRule(_localctx, 30, RULE_from);
		int _la;
		try {
			setState(174);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(164); match(FROM);
				setState(165); match(EHR);
				setState(166); match(IDENTIFIER);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(167); match(FROM);
				setState(168); match(EHR);
				setState(170);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(169); match(IDENTIFIER);
					}
				}

				setState(172); match(CONTAINS);
				setState(173); containsExpr();
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
		enterRule(_localctx, 32, RULE_containsExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); containExpressionBool();
			setState(180);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(177); boolOp();
				setState(178); containsExpr();
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
		enterRule(_localctx, 34, RULE_contains);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); simpleClassExpr();
			setState(185);
			_la = _input.LA(1);
			if (_la==CONTAINS) {
				{
				setState(183); match(CONTAINS);
				setState(184); containsExpr();
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
		enterRule(_localctx, 36, RULE_containExpressionBool);
		try {
			setState(192);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(187); contains();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(188); match(2);
				setState(189); containsExpr();
				setState(190); match(1);
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
		enterRule(_localctx, 38, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
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
		enterRule(_localctx, 40, RULE_simpleClassExpr);
		int _la;
		try {
			setState(201);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(196); match(IDENTIFIER);
				setState(198);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(197); match(IDENTIFIER);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(200); archetypedClassExpr();
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
		enterRule(_localctx, 42, RULE_archetypedClassExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203); match(IDENTIFIER);
			setState(205);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(204); match(IDENTIFIER);
				}
			}

			setState(207); archetypePredicate();
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
		enterRule(_localctx, 44, RULE_archetypePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209); match(OPENBRACKET);
			setState(210); match(ARCHETYPEID);
			setState(211); match(CLOSEBRACKET);
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
		enterRule(_localctx, 46, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213); match(WHERE);
			setState(214); identifiedExpr();
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
		enterRule(_localctx, 48, RULE_identifiedExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216); identifiedExprAnd();
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR || _la==XOR) {
				{
				{
				setState(217); orOp();
				setState(218); identifiedExprAnd();
				}
				}
				setState(224);
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
		enterRule(_localctx, 50, RULE_orOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
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
		enterRule(_localctx, 52, RULE_identifiedExprAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227); identifiedEquality();
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(228); match(AND);
				setState(229); identifiedEquality();
				}
				}
				setState(234);
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
		enterRule(_localctx, 54, RULE_identifiedEquality);
		try {
			setState(247);
			switch (_input.LA(1)) {
			case INTEGER:
			case BOOLEAN:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(235); identifiedOperand();
				setState(236); match(COMPARABLEOPERATOR);
				setState(237); identifiedOperand();
				}
				break;
			case EXISTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(239); match(EXISTS);
				setState(240); identifiedPath();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(241); match(2);
				setState(242); identifiedExpr();
				setState(243); match(1);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 4);
				{
				setState(245); match(NOT);
				setState(246); identifiedEquality();
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
		enterRule(_localctx, 56, RULE_identifiedOperand);
		try {
			setState(251);
			switch (_input.LA(1)) {
			case INTEGER:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(249); operand();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(250); identifiedPath();
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
		enterRule(_localctx, 58, RULE_operand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u0102\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\5\2B\n\2\3\2\3\2\3\3\3\3\5\3H\n\3\3\3\3\3\3\4\3\4\3\4\5\4O\n\4\3\4"+
		"\3\4\3\4\5\4T\n\4\3\5\3\5\3\6\3\6\3\6\7\6[\n\6\f\6\16\6^\13\6\3\7\3\7"+
		"\5\7b\n\7\3\b\3\b\3\b\3\t\3\t\5\ti\n\t\3\t\3\t\5\tm\n\t\3\n\3\n\3\n\7"+
		"\nr\n\n\f\n\16\nu\13\n\3\13\3\13\5\13y\n\13\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\7\r\u0082\n\r\f\r\16\r\u0085\13\r\3\16\3\16\3\16\7\16\u008a\n\16\f"+
		"\16\16\16\u008d\13\16\3\17\3\17\3\17\5\17\u0092\n\17\3\17\3\17\3\17\5"+
		"\17\u0097\n\17\3\17\3\17\5\17\u009b\n\17\3\17\3\17\3\17\3\17\5\17\u00a1"+
		"\n\17\3\20\3\20\5\20\u00a5\n\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00ad"+
		"\n\21\3\21\3\21\5\21\u00b1\n\21\3\22\3\22\3\22\3\22\5\22\u00b7\n\22\3"+
		"\23\3\23\3\23\5\23\u00bc\n\23\3\24\3\24\3\24\3\24\3\24\5\24\u00c3\n\24"+
		"\3\25\3\25\3\26\3\26\5\26\u00c9\n\26\3\26\5\26\u00cc\n\26\3\27\3\27\5"+
		"\27\u00d0\n\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\7\32\u00df\n\32\f\32\16\32\u00e2\13\32\3\33\3\33\3\34\3\34"+
		"\3\34\7\34\u00e9\n\34\f\34\16\34\u00ec\13\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u00fa\n\35\3\36\3\36\5\36\u00fe"+
		"\n\36\3\37\3\37\3\37\2\2 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"+
		"(*,.\60\62\64\668:<\2\5\3\2\24\26\3\2\25\26\4\2\22\23\33\33\u0104\2>\3"+
		"\2\2\2\4E\3\2\2\2\6S\3\2\2\2\bU\3\2\2\2\nW\3\2\2\2\f_\3\2\2\2\16c\3\2"+
		"\2\2\20f\3\2\2\2\22n\3\2\2\2\24v\3\2\2\2\26z\3\2\2\2\30~\3\2\2\2\32\u0086"+
		"\3\2\2\2\34\u00a0\3\2\2\2\36\u00a4\3\2\2\2 \u00b0\3\2\2\2\"\u00b2\3\2"+
		"\2\2$\u00b8\3\2\2\2&\u00c2\3\2\2\2(\u00c4\3\2\2\2*\u00cb\3\2\2\2,\u00cd"+
		"\3\2\2\2.\u00d3\3\2\2\2\60\u00d7\3\2\2\2\62\u00da\3\2\2\2\64\u00e3\3\2"+
		"\2\2\66\u00e5\3\2\2\28\u00f9\3\2\2\2:\u00fd\3\2\2\2<\u00ff\3\2\2\2>?\5"+
		"\4\3\2?A\5 \21\2@B\5\60\31\2A@\3\2\2\2AB\3\2\2\2BC\3\2\2\2CD\7\6\2\2D"+
		"\3\3\2\2\2EG\7\13\2\2FH\5\6\4\2GF\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\5\b\5"+
		"\2J\5\3\2\2\2KL\7\21\2\2LN\7\22\2\2MO\7\17\2\2NM\3\2\2\2NO\3\2\2\2OT\3"+
		"\2\2\2PQ\7\21\2\2QR\7\22\2\2RT\7\20\2\2SK\3\2\2\2SP\3\2\2\2T\7\3\2\2\2"+
		"UV\5\n\6\2V\t\3\2\2\2W\\\5\f\7\2XY\7\16\2\2Y[\5\f\7\2ZX\3\2\2\2[^\3\2"+
		"\2\2\\Z\3\2\2\2\\]\3\2\2\2]\13\3\2\2\2^\\\3\2\2\2_a\5\20\t\2`b\5\16\b"+
		"\2a`\3\2\2\2ab\3\2\2\2b\r\3\2\2\2cd\7\30\2\2de\7\35\2\2e\17\3\2\2\2fh"+
		"\7\35\2\2gi\5\26\f\2hg\3\2\2\2hi\3\2\2\2il\3\2\2\2jk\7\5\2\2km\5\22\n"+
		"\2lj\3\2\2\2lm\3\2\2\2m\21\3\2\2\2ns\5\24\13\2op\7\5\2\2pr\5\24\13\2q"+
		"o\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t\23\3\2\2\2us\3\2\2\2vx\7\35\2"+
		"\2wy\5\26\f\2xw\3\2\2\2xy\3\2\2\2y\25\3\2\2\2z{\7\36\2\2{|\5\30\r\2|}"+
		"\7\37\2\2}\27\3\2\2\2~\u0083\5\32\16\2\177\u0080\7\25\2\2\u0080\u0082"+
		"\5\32\16\2\u0081\177\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\31\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u008b\5\34\17"+
		"\2\u0087\u0088\7\24\2\2\u0088\u008a\5\34\17\2\u0089\u0087\3\2\2\2\u008a"+
		"\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\33\3\2\2"+
		"\2\u008d\u008b\3\2\2\2\u008e\u0091\7\b\2\2\u008f\u0090\7\16\2\2\u0090"+
		"\u0092\7\33\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u00a1\3"+
		"\2\2\2\u0093\u0096\7\34\2\2\u0094\u0095\7\16\2\2\u0095\u0097\7\33\2\2"+
		"\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u00a1\3\2\2\2\u0098\u0099"+
		"\7\16\2\2\u0099\u009b\7\33\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2"+
		"\u009b\u00a1\3\2\2\2\u009c\u009d\5\36\20\2\u009d\u009e\7\31\2\2\u009e"+
		"\u009f\5\36\20\2\u009f\u00a1\3\2\2\2\u00a0\u008e\3\2\2\2\u00a0\u0093\3"+
		"\2\2\2\u00a0\u009a\3\2\2\2\u00a0\u009c\3\2\2\2\u00a1\35\3\2\2\2\u00a2"+
		"\u00a5\5\22\n\2\u00a3\u00a5\5<\37\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3"+
		"\2\2\2\u00a5\37\3\2\2\2\u00a6\u00a7\7\t\2\2\u00a7\u00a8\7\r\2\2\u00a8"+
		"\u00b1\7\35\2\2\u00a9\u00aa\7\t\2\2\u00aa\u00ac\7\r\2\2\u00ab\u00ad\7"+
		"\35\2\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00af\7\n\2\2\u00af\u00b1\5\"\22\2\u00b0\u00a6\3\2\2\2\u00b0\u00a9\3"+
		"\2\2\2\u00b1!\3\2\2\2\u00b2\u00b6\5&\24\2\u00b3\u00b4\5(\25\2\u00b4\u00b5"+
		"\5\"\22\2\u00b5\u00b7\3\2\2\2\u00b6\u00b3\3\2\2\2\u00b6\u00b7\3\2\2\2"+
		"\u00b7#\3\2\2\2\u00b8\u00bb\5*\26\2\u00b9\u00ba\7\n\2\2\u00ba\u00bc\5"+
		"\"\22\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc%\3\2\2\2\u00bd\u00c3"+
		"\5$\23\2\u00be\u00bf\7\4\2\2\u00bf\u00c0\5\"\22\2\u00c0\u00c1\7\3\2\2"+
		"\u00c1\u00c3\3\2\2\2\u00c2\u00bd\3\2\2\2\u00c2\u00be\3\2\2\2\u00c3\'\3"+
		"\2\2\2\u00c4\u00c5\t\2\2\2\u00c5)\3\2\2\2\u00c6\u00c8\7\35\2\2\u00c7\u00c9"+
		"\7\35\2\2\u00c8\u00c7\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cc\3\2\2\2"+
		"\u00ca\u00cc\5,\27\2\u00cb\u00c6\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc+\3"+
		"\2\2\2\u00cd\u00cf\7\35\2\2\u00ce\u00d0\7\35\2\2\u00cf\u00ce\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\5.\30\2\u00d2-\3\2\2\2"+
		"\u00d3\u00d4\7\36\2\2\u00d4\u00d5\7\34\2\2\u00d5\u00d6\7\37\2\2\u00d6"+
		"/\3\2\2\2\u00d7\u00d8\7\f\2\2\u00d8\u00d9\5\62\32\2\u00d9\61\3\2\2\2\u00da"+
		"\u00e0\5\66\34\2\u00db\u00dc\5\64\33\2\u00dc\u00dd\5\66\34\2\u00dd\u00df"+
		"\3\2\2\2\u00de\u00db\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\63\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4\t\3\2"+
		"\2\u00e4\65\3\2\2\2\u00e5\u00ea\58\35\2\u00e6\u00e7\7\24\2\2\u00e7\u00e9"+
		"\58\35\2\u00e8\u00e6\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea"+
		"\u00eb\3\2\2\2\u00eb\67\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\5:\36"+
		"\2\u00ee\u00ef\7\31\2\2\u00ef\u00f0\5:\36\2\u00f0\u00fa\3\2\2\2\u00f1"+
		"\u00f2\7\32\2\2\u00f2\u00fa\5\20\t\2\u00f3\u00f4\7\4\2\2\u00f4\u00f5\5"+
		"\62\32\2\u00f5\u00f6\7\3\2\2\u00f6\u00fa\3\2\2\2\u00f7\u00f8\7\27\2\2"+
		"\u00f8\u00fa\58\35\2\u00f9\u00ed\3\2\2\2\u00f9\u00f1\3\2\2\2\u00f9\u00f3"+
		"\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa9\3\2\2\2\u00fb\u00fe\5<\37\2\u00fc"+
		"\u00fe\5\20\t\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc\3\2\2\2\u00fe;\3\2\2\2"+
		"\u00ff\u0100\t\4\2\2\u0100=\3\2\2\2\37AGNS\\ahlsx\u0083\u008b\u0091\u0096"+
		"\u009a\u00a0\u00a4\u00ac\u00b0\u00b6\u00bb\u00c2\u00c8\u00cb\u00cf\u00e0"+
		"\u00ea\u00f9\u00fd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}