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
		WHERE=10, HAVING=11, EHR=12, SYSTEM=13, COMMA=14, FORWARD=15, BACKWARD=16, 
		TOP=17, INTEGER=18, FLOAT=19, DATE=20, BOOLEAN=21, AND=22, OR=23, XOR=24, 
		NOT=25, AS=26, ALL=27, VERSIONS=28, OF=29, WITH=30, DESCENDANTS=31, MERGED=32, 
		COMPARABLEOPERATOR=33, EXISTS=34, STRING=35, ARCHETYPEID=36, IDENTIFIER=37, 
		OPENBRACKET=38, CLOSEBRACKET=39;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "'('", "'/'", "';'", "WS", "NODEID", "'FROM'", "'CONTAINS'", 
		"'SELECT'", "'WHERE'", "'HAVING'", "'EHR'", "'SYSTEM'", "','", "FORWARD", 
		"BACKWARD", "TOP", "INTEGER", "FLOAT", "DATE", "BOOLEAN", "AND", "OR", 
		"XOR", "NOT", "AS", "'ALL'", "'VERSIONS'", "'OF'", "'WITH'", "'DESCENDANTS'", 
		"'MERGED'", "COMPARABLEOPERATOR", "EXISTS", "STRING", "ARCHETYPEID", "IDENTIFIER", 
		"'['", "']'"
	};
	public static final int
		RULE_query = 0, RULE_select = 1, RULE_merged = 2, RULE_top = 3, RULE_selectExpr = 4, 
		RULE_identifiedPathSeq = 5, RULE_selectVar = 6, RULE_asIdentifier = 7, 
		RULE_withDescendants = 8, RULE_identifiedPath = 9, RULE_objectPath = 10, 
		RULE_pathPart = 11, RULE_nodePredicate = 12, RULE_nodePredicateOr = 13, 
		RULE_nodePredicateAnd = 14, RULE_nodePredicateComparable = 15, RULE_predicateOperand = 16, 
		RULE_from = 17, RULE_containsExpr = 18, RULE_contains = 19, RULE_allVersions = 20, 
		RULE_containExpressionBool = 21, RULE_boolOp = 22, RULE_simpleClassExpr = 23, 
		RULE_archetypedClassExpr = 24, RULE_archetypePredicate = 25, RULE_where = 26, 
		RULE_having = 27, RULE_identifiedExpr = 28, RULE_orOp = 29, RULE_identifiedExprAnd = 30, 
		RULE_identifiedEquality = 31, RULE_identifiedOperand = 32, RULE_operand = 33;
	public static final String[] ruleNames = {
		"query", "select", "merged", "top", "selectExpr", "identifiedPathSeq", 
		"selectVar", "asIdentifier", "withDescendants", "identifiedPath", "objectPath", 
		"pathPart", "nodePredicate", "nodePredicateOr", "nodePredicateAnd", "nodePredicateComparable", 
		"predicateOperand", "from", "containsExpr", "contains", "allVersions", 
		"containExpressionBool", "boolOp", "simpleClassExpr", "archetypedClassExpr", 
		"archetypePredicate", "where", "having", "identifiedExpr", "orOp", "identifiedExprAnd", 
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
		public HavingContext having() {
			return getRuleContext(HavingContext.class,0);
		}
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
			setState(68); select();
			setState(69); from();
			setState(71);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(70); where();
				}
			}

			setState(74);
			_la = _input.LA(1);
			if (_la==HAVING) {
				{
				setState(73); having();
				}
			}

			setState(76); match(4);
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
		public MergedContext merged() {
			return getRuleContext(MergedContext.class,0);
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
			setState(78); match(SELECT);
			setState(80);
			_la = _input.LA(1);
			if (_la==TOP) {
				{
				setState(79); top();
				}
			}

			setState(83);
			_la = _input.LA(1);
			if (_la==MERGED) {
				{
				setState(82); merged();
				}
			}

			setState(85); selectExpr();
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

	public static class MergedContext extends ParserRuleContext {
		public AsIdentifierContext asIdentifier() {
			return getRuleContext(AsIdentifierContext.class,0);
		}
		public TerminalNode MERGED() { return getToken(SEQLParser.MERGED, 0); }
		public MergedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_merged; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterMerged(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitMerged(this);
		}
	}

	public final MergedContext merged() throws RecognitionException {
		MergedContext _localctx = new MergedContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_merged);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); match(MERGED);
			setState(89);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(88); asIdentifier();
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
		enterRule(_localctx, 6, RULE_top);
		int _la;
		try {
			setState(99);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91); match(TOP);
				setState(92); match(INTEGER);
				setState(94);
				_la = _input.LA(1);
				if (_la==FORWARD) {
					{
					setState(93); match(FORWARD);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(96); match(TOP);
				setState(97); match(INTEGER);
				setState(98); match(BACKWARD);
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
		enterRule(_localctx, 8, RULE_selectExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); identifiedPathSeq();
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
		enterRule(_localctx, 10, RULE_identifiedPathSeq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); selectVar();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(104); match(COMMA);
				setState(105); selectVar();
				}
				}
				setState(110);
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
		enterRule(_localctx, 12, RULE_selectVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); identifiedPath();
			setState(113);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(112); asIdentifier();
				}
			}

			setState(116);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(115); withDescendants();
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
		enterRule(_localctx, 14, RULE_asIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); match(AS);
			setState(119); match(IDENTIFIER);
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
		enterRule(_localctx, 16, RULE_withDescendants);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121); match(WITH);
			setState(122); match(DESCENDANTS);
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
		enterRule(_localctx, 18, RULE_identifiedPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); match(IDENTIFIER);
			setState(126);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(125); nodePredicate();
				}
			}

			setState(130);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(128); match(3);
				setState(129); objectPath();
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
		enterRule(_localctx, 20, RULE_objectPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132); pathPart();
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==3) {
				{
				{
				setState(133); match(3);
				setState(134); pathPart();
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
		enterRule(_localctx, 22, RULE_pathPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140); match(IDENTIFIER);
			setState(142);
			_la = _input.LA(1);
			if (_la==OPENBRACKET) {
				{
				setState(141); nodePredicate();
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
		enterRule(_localctx, 24, RULE_nodePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144); match(OPENBRACKET);
			setState(145); nodePredicateOr();
			setState(146); match(CLOSEBRACKET);
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
		enterRule(_localctx, 26, RULE_nodePredicateOr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); nodePredicateAnd();
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(149); match(OR);
				setState(150); nodePredicateAnd();
				}
				}
				setState(155);
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
		enterRule(_localctx, 28, RULE_nodePredicateAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156); nodePredicateComparable();
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(157); match(AND);
				setState(158); nodePredicateComparable();
				}
				}
				setState(163);
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
		enterRule(_localctx, 30, RULE_nodePredicateComparable);
		int _la;
		try {
			setState(182);
			switch (_input.LA(1)) {
			case NODEID:
				enterOuterAlt(_localctx, 1);
				{
				setState(164); match(NODEID);
				setState(167);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(165); match(COMMA);
					{
					setState(166); match(STRING);
					}
					}
				}

				}
				break;
			case ARCHETYPEID:
				enterOuterAlt(_localctx, 2);
				{
				setState(169); match(ARCHETYPEID);
				setState(172);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(170); match(COMMA);
					{
					setState(171); match(STRING);
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
				setState(176);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(174); match(COMMA);
					{
					setState(175); match(STRING);
					}
					}
				}

				}
				break;
			case INTEGER:
			case FLOAT:
			case DATE:
			case BOOLEAN:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(178); predicateOperand();
				{
				setState(179); match(COMPARABLEOPERATOR);
				setState(180); predicateOperand();
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
		enterRule(_localctx, 32, RULE_predicateOperand);
		try {
			setState(186);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(184); objectPath();
				}
				break;
			case INTEGER:
			case FLOAT:
			case DATE:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(185); operand();
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
		enterRule(_localctx, 34, RULE_from);
		int _la;
		try {
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(188); match(FROM);
				setState(189); match(EHR);
				setState(190); match(SYSTEM);
				setState(191); match(IDENTIFIER);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(192); match(FROM);
				setState(193); match(EHR);
				setState(194); match(SYSTEM);
				setState(196);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(195); match(IDENTIFIER);
					}
				}

				setState(198); match(CONTAINS);
				setState(199); containsExpr();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(200); match(FROM);
				setState(201); match(EHR);
				setState(202); match(IDENTIFIER);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(203); match(FROM);
				setState(204); match(EHR);
				setState(206);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(205); match(IDENTIFIER);
					}
				}

				setState(208); match(CONTAINS);
				setState(209); containsExpr();
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
		enterRule(_localctx, 36, RULE_containsExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212); containExpressionBool();
			setState(216);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(213); boolOp();
				setState(214); containsExpr();
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
		enterRule(_localctx, 38, RULE_contains);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218); simpleClassExpr();
			setState(221);
			_la = _input.LA(1);
			if (_la==CONTAINS) {
				{
				setState(219); match(CONTAINS);
				setState(220); containsExpr();
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
		enterRule(_localctx, 40, RULE_allVersions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223); match(ALL);
			setState(224); match(VERSIONS);
			setState(225); match(OF);
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
		enterRule(_localctx, 42, RULE_containExpressionBool);
		try {
			setState(232);
			switch (_input.LA(1)) {
			case ALL:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(227); contains();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(228); match(2);
				setState(229); containsExpr();
				setState(230); match(1);
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
		enterRule(_localctx, 44, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
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
		enterRule(_localctx, 46, RULE_simpleClassExpr);
		int _la;
		try {
			setState(244);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				_la = _input.LA(1);
				if (_la==ALL) {
					{
					setState(236); allVersions();
					}
				}

				setState(239); match(IDENTIFIER);
				setState(241);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(240); match(IDENTIFIER);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(243); archetypedClassExpr();
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
		enterRule(_localctx, 48, RULE_archetypedClassExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			_la = _input.LA(1);
			if (_la==ALL) {
				{
				setState(246); allVersions();
				}
			}

			setState(249); match(IDENTIFIER);
			setState(251);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(250); match(IDENTIFIER);
				}
			}

			setState(253); archetypePredicate();
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
		enterRule(_localctx, 50, RULE_archetypePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255); match(OPENBRACKET);
			setState(256); match(ARCHETYPEID);
			setState(257); match(CLOSEBRACKET);
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
		enterRule(_localctx, 52, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259); match(WHERE);
			setState(260); identifiedExpr();
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

	public static class HavingContext extends ParserRuleContext {
		public TerminalNode HAVING() { return getToken(SEQLParser.HAVING, 0); }
		public IdentifiedExprContext identifiedExpr() {
			return getRuleContext(IdentifiedExprContext.class,0);
		}
		public HavingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_having; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).enterHaving(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SEQLListener ) ((SEQLListener)listener).exitHaving(this);
		}
	}

	public final HavingContext having() throws RecognitionException {
		HavingContext _localctx = new HavingContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_having);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262); match(HAVING);
			setState(263); identifiedExpr();
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
		enterRule(_localctx, 56, RULE_identifiedExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265); identifiedExprAnd();
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR || _la==XOR) {
				{
				{
				setState(266); orOp();
				setState(267); identifiedExprAnd();
				}
				}
				setState(273);
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
		enterRule(_localctx, 58, RULE_orOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
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
		enterRule(_localctx, 60, RULE_identifiedExprAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276); identifiedEquality();
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(277); match(AND);
				setState(278); identifiedEquality();
				}
				}
				setState(283);
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
		enterRule(_localctx, 62, RULE_identifiedEquality);
		try {
			setState(296);
			switch (_input.LA(1)) {
			case INTEGER:
			case FLOAT:
			case DATE:
			case BOOLEAN:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(284); identifiedOperand();
				setState(285); match(COMPARABLEOPERATOR);
				setState(286); identifiedOperand();
				}
				break;
			case EXISTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(288); match(EXISTS);
				setState(289); identifiedPath();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(290); match(2);
				setState(291); identifiedExpr();
				setState(292); match(1);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 4);
				{
				setState(294); match(NOT);
				setState(295); identifiedEquality();
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
		enterRule(_localctx, 64, RULE_identifiedOperand);
		try {
			setState(300);
			switch (_input.LA(1)) {
			case INTEGER:
			case FLOAT:
			case DATE:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(298); operand();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(299); identifiedPath();
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
		public TerminalNode DATE() { return getToken(SEQLParser.DATE, 0); }
		public TerminalNode BOOLEAN() { return getToken(SEQLParser.BOOLEAN, 0); }
		public TerminalNode FLOAT() { return getToken(SEQLParser.FLOAT, 0); }
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
		enterRule(_localctx, 66, RULE_operand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << FLOAT) | (1L << DATE) | (1L << BOOLEAN) | (1L << STRING))) != 0)) ) {
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3)\u0133\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\5\2J\n\2\3\2\5\2M\n\2\3\2\3\2\3\3\3\3\5"+
		"\3S\n\3\3\3\5\3V\n\3\3\3\3\3\3\4\3\4\5\4\\\n\4\3\5\3\5\3\5\5\5a\n\5\3"+
		"\5\3\5\3\5\5\5f\n\5\3\6\3\6\3\7\3\7\3\7\7\7m\n\7\f\7\16\7p\13\7\3\b\3"+
		"\b\5\bt\n\b\3\b\5\bw\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\5\13\u0081"+
		"\n\13\3\13\3\13\5\13\u0085\n\13\3\f\3\f\3\f\7\f\u008a\n\f\f\f\16\f\u008d"+
		"\13\f\3\r\3\r\5\r\u0091\n\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\7\17\u009a"+
		"\n\17\f\17\16\17\u009d\13\17\3\20\3\20\3\20\7\20\u00a2\n\20\f\20\16\20"+
		"\u00a5\13\20\3\21\3\21\3\21\5\21\u00aa\n\21\3\21\3\21\3\21\5\21\u00af"+
		"\n\21\3\21\3\21\5\21\u00b3\n\21\3\21\3\21\3\21\3\21\5\21\u00b9\n\21\3"+
		"\22\3\22\5\22\u00bd\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u00c7\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00d1\n\23\3"+
		"\23\3\23\5\23\u00d5\n\23\3\24\3\24\3\24\3\24\5\24\u00db\n\24\3\25\3\25"+
		"\3\25\5\25\u00e0\n\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\5\27"+
		"\u00eb\n\27\3\30\3\30\3\31\5\31\u00f0\n\31\3\31\3\31\5\31\u00f4\n\31\3"+
		"\31\5\31\u00f7\n\31\3\32\5\32\u00fa\n\32\3\32\3\32\5\32\u00fe\n\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\7\36\u0110\n\36\f\36\16\36\u0113\13\36\3\37\3\37\3 \3 \3 \7 \u011a"+
		"\n \f \16 \u011d\13 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u012b\n!\3"+
		"\"\3\"\5\"\u012f\n\"\3#\3#\3#\2\2$\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@BD\2\5\3\2\30\32\3\2\31\32\4\2\24\27%%\u013a"+
		"\2F\3\2\2\2\4P\3\2\2\2\6Y\3\2\2\2\be\3\2\2\2\ng\3\2\2\2\fi\3\2\2\2\16"+
		"q\3\2\2\2\20x\3\2\2\2\22{\3\2\2\2\24~\3\2\2\2\26\u0086\3\2\2\2\30\u008e"+
		"\3\2\2\2\32\u0092\3\2\2\2\34\u0096\3\2\2\2\36\u009e\3\2\2\2 \u00b8\3\2"+
		"\2\2\"\u00bc\3\2\2\2$\u00d4\3\2\2\2&\u00d6\3\2\2\2(\u00dc\3\2\2\2*\u00e1"+
		"\3\2\2\2,\u00ea\3\2\2\2.\u00ec\3\2\2\2\60\u00f6\3\2\2\2\62\u00f9\3\2\2"+
		"\2\64\u0101\3\2\2\2\66\u0105\3\2\2\28\u0108\3\2\2\2:\u010b\3\2\2\2<\u0114"+
		"\3\2\2\2>\u0116\3\2\2\2@\u012a\3\2\2\2B\u012e\3\2\2\2D\u0130\3\2\2\2F"+
		"G\5\4\3\2GI\5$\23\2HJ\5\66\34\2IH\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KM\58\35"+
		"\2LK\3\2\2\2LM\3\2\2\2MN\3\2\2\2NO\7\6\2\2O\3\3\2\2\2PR\7\13\2\2QS\5\b"+
		"\5\2RQ\3\2\2\2RS\3\2\2\2SU\3\2\2\2TV\5\6\4\2UT\3\2\2\2UV\3\2\2\2VW\3\2"+
		"\2\2WX\5\n\6\2X\5\3\2\2\2Y[\7\"\2\2Z\\\5\20\t\2[Z\3\2\2\2[\\\3\2\2\2\\"+
		"\7\3\2\2\2]^\7\23\2\2^`\7\24\2\2_a\7\21\2\2`_\3\2\2\2`a\3\2\2\2af\3\2"+
		"\2\2bc\7\23\2\2cd\7\24\2\2df\7\22\2\2e]\3\2\2\2eb\3\2\2\2f\t\3\2\2\2g"+
		"h\5\f\7\2h\13\3\2\2\2in\5\16\b\2jk\7\20\2\2km\5\16\b\2lj\3\2\2\2mp\3\2"+
		"\2\2nl\3\2\2\2no\3\2\2\2o\r\3\2\2\2pn\3\2\2\2qs\5\24\13\2rt\5\20\t\2s"+
		"r\3\2\2\2st\3\2\2\2tv\3\2\2\2uw\5\22\n\2vu\3\2\2\2vw\3\2\2\2w\17\3\2\2"+
		"\2xy\7\34\2\2yz\7\'\2\2z\21\3\2\2\2{|\7 \2\2|}\7!\2\2}\23\3\2\2\2~\u0080"+
		"\7\'\2\2\177\u0081\5\32\16\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0084\3\2\2\2\u0082\u0083\7\5\2\2\u0083\u0085\5\26\f\2\u0084\u0082\3"+
		"\2\2\2\u0084\u0085\3\2\2\2\u0085\25\3\2\2\2\u0086\u008b\5\30\r\2\u0087"+
		"\u0088\7\5\2\2\u0088\u008a\5\30\r\2\u0089\u0087\3\2\2\2\u008a\u008d\3"+
		"\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\27\3\2\2\2\u008d"+
		"\u008b\3\2\2\2\u008e\u0090\7\'\2\2\u008f\u0091\5\32\16\2\u0090\u008f\3"+
		"\2\2\2\u0090\u0091\3\2\2\2\u0091\31\3\2\2\2\u0092\u0093\7(\2\2\u0093\u0094"+
		"\5\34\17\2\u0094\u0095\7)\2\2\u0095\33\3\2\2\2\u0096\u009b\5\36\20\2\u0097"+
		"\u0098\7\31\2\2\u0098\u009a\5\36\20\2\u0099\u0097\3\2\2\2\u009a\u009d"+
		"\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\35\3\2\2\2\u009d"+
		"\u009b\3\2\2\2\u009e\u00a3\5 \21\2\u009f\u00a0\7\30\2\2\u00a0\u00a2\5"+
		" \21\2\u00a1\u009f\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\37\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a9\7\b\2"+
		"\2\u00a7\u00a8\7\20\2\2\u00a8\u00aa\7%\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa"+
		"\3\2\2\2\u00aa\u00b9\3\2\2\2\u00ab\u00ae\7&\2\2\u00ac\u00ad\7\20\2\2\u00ad"+
		"\u00af\7%\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b9\3\2"+
		"\2\2\u00b0\u00b1\7\20\2\2\u00b1\u00b3\7%\2\2\u00b2\u00b0\3\2\2\2\u00b2"+
		"\u00b3\3\2\2\2\u00b3\u00b9\3\2\2\2\u00b4\u00b5\5\"\22\2\u00b5\u00b6\7"+
		"#\2\2\u00b6\u00b7\5\"\22\2\u00b7\u00b9\3\2\2\2\u00b8\u00a6\3\2\2\2\u00b8"+
		"\u00ab\3\2\2\2\u00b8\u00b2\3\2\2\2\u00b8\u00b4\3\2\2\2\u00b9!\3\2\2\2"+
		"\u00ba\u00bd\5\26\f\2\u00bb\u00bd\5D#\2\u00bc\u00ba\3\2\2\2\u00bc\u00bb"+
		"\3\2\2\2\u00bd#\3\2\2\2\u00be\u00bf\7\t\2\2\u00bf\u00c0\7\16\2\2\u00c0"+
		"\u00c1\7\17\2\2\u00c1\u00d5\7\'\2\2\u00c2\u00c3\7\t\2\2\u00c3\u00c4\7"+
		"\16\2\2\u00c4\u00c6\7\17\2\2\u00c5\u00c7\7\'\2\2\u00c6\u00c5\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\7\n\2\2\u00c9\u00d5\5&"+
		"\24\2\u00ca\u00cb\7\t\2\2\u00cb\u00cc\7\16\2\2\u00cc\u00d5\7\'\2\2\u00cd"+
		"\u00ce\7\t\2\2\u00ce\u00d0\7\16\2\2\u00cf\u00d1\7\'\2\2\u00d0\u00cf\3"+
		"\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\7\n\2\2\u00d3"+
		"\u00d5\5&\24\2\u00d4\u00be\3\2\2\2\u00d4\u00c2\3\2\2\2\u00d4\u00ca\3\2"+
		"\2\2\u00d4\u00cd\3\2\2\2\u00d5%\3\2\2\2\u00d6\u00da\5,\27\2\u00d7\u00d8"+
		"\5.\30\2\u00d8\u00d9\5&\24\2\u00d9\u00db\3\2\2\2\u00da\u00d7\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\'\3\2\2\2\u00dc\u00df\5\60\31\2\u00dd\u00de\7\n\2"+
		"\2\u00de\u00e0\5&\24\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0)"+
		"\3\2\2\2\u00e1\u00e2\7\35\2\2\u00e2\u00e3\7\36\2\2\u00e3\u00e4\7\37\2"+
		"\2\u00e4+\3\2\2\2\u00e5\u00eb\5(\25\2\u00e6\u00e7\7\4\2\2\u00e7\u00e8"+
		"\5&\24\2\u00e8\u00e9\7\3\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00e5\3\2\2\2\u00ea"+
		"\u00e6\3\2\2\2\u00eb-\3\2\2\2\u00ec\u00ed\t\2\2\2\u00ed/\3\2\2\2\u00ee"+
		"\u00f0\5*\26\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f1\u00f3\7\'\2\2\u00f2\u00f4\7\'\2\2\u00f3\u00f2\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f7\5\62\32\2\u00f6\u00ef\3"+
		"\2\2\2\u00f6\u00f5\3\2\2\2\u00f7\61\3\2\2\2\u00f8\u00fa\5*\26\2\u00f9"+
		"\u00f8\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\7\'"+
		"\2\2\u00fc\u00fe\7\'\2\2\u00fd\u00fc\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff\u0100\5\64\33\2\u0100\63\3\2\2\2\u0101\u0102\7(\2"+
		"\2\u0102\u0103\7&\2\2\u0103\u0104\7)\2\2\u0104\65\3\2\2\2\u0105\u0106"+
		"\7\f\2\2\u0106\u0107\5:\36\2\u0107\67\3\2\2\2\u0108\u0109\7\r\2\2\u0109"+
		"\u010a\5:\36\2\u010a9\3\2\2\2\u010b\u0111\5> \2\u010c\u010d\5<\37\2\u010d"+
		"\u010e\5> \2\u010e\u0110\3\2\2\2\u010f\u010c\3\2\2\2\u0110\u0113\3\2\2"+
		"\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112;\3\2\2\2\u0113\u0111"+
		"\3\2\2\2\u0114\u0115\t\3\2\2\u0115=\3\2\2\2\u0116\u011b\5@!\2\u0117\u0118"+
		"\7\30\2\2\u0118\u011a\5@!\2\u0119\u0117\3\2\2\2\u011a\u011d\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c?\3\2\2\2\u011d\u011b\3\2\2\2"+
		"\u011e\u011f\5B\"\2\u011f\u0120\7#\2\2\u0120\u0121\5B\"\2\u0121\u012b"+
		"\3\2\2\2\u0122\u0123\7$\2\2\u0123\u012b\5\24\13\2\u0124\u0125\7\4\2\2"+
		"\u0125\u0126\5:\36\2\u0126\u0127\7\3\2\2\u0127\u012b\3\2\2\2\u0128\u0129"+
		"\7\33\2\2\u0129\u012b\5@!\2\u012a\u011e\3\2\2\2\u012a\u0122\3\2\2\2\u012a"+
		"\u0124\3\2\2\2\u012a\u0128\3\2\2\2\u012bA\3\2\2\2\u012c\u012f\5D#\2\u012d"+
		"\u012f\5\24\13\2\u012e\u012c\3\2\2\2\u012e\u012d\3\2\2\2\u012fC\3\2\2"+
		"\2\u0130\u0131\t\4\2\2\u0131E\3\2\2\2&ILRU[`ensv\u0080\u0084\u008b\u0090"+
		"\u009b\u00a3\u00a9\u00ae\u00b2\u00b8\u00bc\u00c6\u00d0\u00d4\u00da\u00df"+
		"\u00ea\u00ef\u00f3\u00f6\u00f9\u00fd\u0111\u011b\u012a\u012e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}