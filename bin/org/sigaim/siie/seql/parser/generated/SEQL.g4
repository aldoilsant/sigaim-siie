grammar SEQL;

@header {
import org.sigaim.siie.seql.parser.model.SEQLFromCondition.SEQLFromComponent;
import org.sigaim.siie.seql.parser.model.SEQLOperation;
import org.sigaim.siie.seql.parser.model.SEQLEvaluable;
import org.sigaim.siie.seql.parser.model.SEQLPrimitive;
import org.sigaim.siie.seql.parser.model.SEQLPath;


}
//Lexer
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
NODEID  : 'at' DIGIT+ ('.' DIGIT+)*;
FROM: 'FROM';
CONTAINS: 'CONTAINS';
SELECT: 'SELECT';
WHERE: 'WHERE';
EHR : ('E'|'e')('H'|'h')('R'|'r');
COMMA   :       ',';
FORWARD : ('F'|'f')('O'|'o')('R'|'r')('W'|'w')('A'|'a')('R'|'r')('D'|'d') ;
BACKWARD : ('B'|'b')('A'|'a')('C'|'c')('K'|'k')('W'|'w')('A'|'a')('R'|'r')('D'|'d') ;
TOP : ('T'|'t')('O'|'o')('P'|'p') ;
INTEGER :       '-'? DIGIT+;
BOOLEAN :       'true' | 'false' | 'TRUE' | 'FALSE' ;

AND : ('A'|'a')('N'|'n')('D'|'d') ;
OR : ('O'|'o')('R'|'r') ;
XOR : ('X'|'x')('O'|'o')('R'|'r') ;
NOT : ('N'|'n')('O'|'o')('T'|'t') ;
AS : ('A'|'a')('S'|'s') ;

COMPARABLEOPERATOR
        :       '=' | '!=' | '>' | '>=' | '<' | '<='
        ;
EXISTS: ('E'|'e')('X'|'x')('I'|'i')('S'|'s')('T'|'t')('S'|'s') ;
   
STRING  
        :  '\'' ( ESC_SEQ | ~('\\'|'\'') )* '\''
        |  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
        ; 
        


ARCHETYPEID : ALPHANUM+ '-' ALPHANUM+ '-' (LETTER|'_')+ '.' (IDCHAR|'-')+ '.v' DIGIT+ ('.' DIGIT+)?;
IDENTIFIER :	('a'|'A') (ALPHANUM|'_')* | 	LETTERMINUSA IDCHAR* ;
fragment LETTERMINUSA :	'b'..'z'|'B'..'Z';
fragment LETTER :	'a'..'z'|'A'..'Z';
fragment IDCHAR	:	ALPHANUM|'_';
fragment ALPHANUM :	LETTER|DIGIT;
fragment DIGIT	:	'0'..'9';
fragment ESC_SEQ 
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') |   UNICODE_ESC;  
fragment UNICODE_ESC :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;
OPENBRACKET :	'['; 
CLOSEBRACKET :	']'; 
query : select from where? ';' ;

select : SELECT top? selectExpr ;

//top integer forward backward
top : TOP INTEGER FORWARD? |
      TOP INTEGER BACKWARD;

selectExpr : identifiedPathSeq;
identifiedPathSeq: selectVar (',' selectVar)*; 
selectVar: identifiedPath asIdentifier?;
asIdentifier: AS IDENTIFIER;

identifiedPath locals [SEQLPath path]: IDENTIFIER nodePredicate? ('/' objectPath)?; 

objectPath : pathPart ('/' pathPart)*;
 
pathPart : IDENTIFIER nodePredicate?;

nodePredicate: OPENBRACKET nodePredicateOr CLOSEBRACKET;
nodePredicateOr : nodePredicateAnd (OR nodePredicateAnd)*;
nodePredicateAnd :nodePredicateComparable (AND nodePredicateComparable)*;

nodePredicateComparable
        : NODEID (COMMA (STRING))?
        | ARCHETYPEID (COMMA (STRING))?;
 


from    :  FROM EHR CONTAINS containsExpr ;

 
containsExpr locals[ SEQLOperation operation]: containExpressionBool (boolOp containsExpr)?;

contains locals[SEQLOperation containsOperation] : simpleClassExpr (CONTAINS containsExpr)?;

containExpressionBool locals[ SEQLOperation operation] : contains | '(' containsExpr ')';
        

boolOp : AND | OR | XOR;


simpleClassExpr locals[SEQLFromComponent component]
        : IDENTIFIER IDENTIFIER?  
        | archetypedClassExpr;
archetypedClassExpr
        : IDENTIFIER IDENTIFIER? archetypePredicate; 
archetypePredicate
        : OPENBRACKET ARCHETYPEID CLOSEBRACKET;
        
where : WHERE identifiedExpr;

identifiedExpr locals[SEQLOperation operation]
        : identifiedExprAnd (orOp identifiedExprAnd)*;
orOp : OR | XOR;

identifiedExprAnd locals[SEQLOperation operation]
        : identifiedEquality (AND identifiedEquality)*;
        
identifiedEquality locals[SEQLOperation operation]
        :identifiedOperand COMPARABLEOPERATOR identifiedOperand
        | EXISTS identifiedPath  
        | '(' identifiedExpr ')'
        | NOT identifiedEquality
        ;
        
identifiedOperand locals[SEQLEvaluable evaluable]
        : operand | identifiedPath;
        
operand locals[SEQLPrimitive primitive]: STRING | INTEGER | BOOLEAN;

        