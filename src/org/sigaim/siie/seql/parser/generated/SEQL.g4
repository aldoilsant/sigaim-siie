grammar SEQL;

@header {
package org.sigaim.siie.seql.parser.generated;
import org.sigaim.siie.seql.model.SEQLFromComponent;
import org.sigaim.siie.seql.model.SEQLOperation;
import org.sigaim.siie.seql.model.SEQLEvaluable;
import org.sigaim.siie.seql.model.SEQLPrimitive;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.seql.model.SEQLPathPredicate;
}

//Lexer
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
NODEID  : 'at' DIGIT+ ('.' DIGIT+)*;
FROM: 'FROM';
CONTAINS: 'CONTAINS';
SELECT: 'SELECT';
WHERE: 'WHERE';
HAVING: 'HAVING';
EHR : 'EHR';
SYSTEM: 'SYSTEM';
COMMA   :       ',';
FORWARD : ('F'|'f')('O'|'o')('R'|'r')('W'|'w')('A'|'a')('R'|'r')('D'|'d') ;
BACKWARD : ('B'|'b')('A'|'a')('C'|'c')('K'|'k')('W'|'w')('A'|'a')('R'|'r')('D'|'d') ;
TOP : ('T'|'t')('O'|'o')('P'|'p') ;
INTEGER :       '-'? DIGIT+;
FLOAT: '-'? DIGIT+ '.' DIGIT+;
//"2014-08-19T22:08:05.186+02:00"
DATE    :       '\'' DIGIT DIGIT DIGIT DIGIT '-' DIGIT DIGIT '-' DIGIT DIGIT 'T' DIGIT DIGIT ':' DIGIT DIGIT ':' DIGIT DIGIT  '.' DIGIT DIGIT DIGIT '+' DIGIT DIGIT ':' DIGIT DIGIT '\'';
BOOLEAN :       'true' | 'false' | 'TRUE' | 'FALSE' ;

AND : ('A'|'a')('N'|'n')('D'|'d') ;
OR : ('O'|'o')('R'|'r') ;
XOR : ('X'|'x')('O'|'o')('R'|'r') ;
NOT : ('N'|'n')('O'|'o')('T'|'t') ;
AS : ('A'|'a')('S'|'s') ;
ALL: 'ALL';
VERSIONS: 'VERSIONS';
OF: 'OF';
WITH: 'WITH';
DESCENDANTS: 'DESCENDANTS';
MERGED: 'MERGED';

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
query : select from where? having? ';' ;

select : SELECT top? merged? selectExpr ;

merged: MERGED asIdentifier? ; 

//top integer forward backward
top : TOP INTEGER FORWARD? |
      TOP INTEGER BACKWARD;

selectExpr : identifiedPathSeq;
identifiedPathSeq: selectVar (',' selectVar)*; 
selectVar: identifiedPath asIdentifier? withDescendants?;
asIdentifier: AS IDENTIFIER;
withDescendants: WITH DESCENDANTS;

identifiedPath locals [SEQLPath path]: IDENTIFIER nodePredicate? ('/' objectPath)?; 

objectPath locals [SEQLPath path] : pathPart ('/' pathPart)*;
 
pathPart locals [SEQLPathComponent pathComponent]: IDENTIFIER nodePredicate?;

nodePredicate locals [SEQLPathPredicate pathPredicate]: OPENBRACKET nodePredicateOr CLOSEBRACKET;
nodePredicateOr locals [SEQLEvaluable evaluable] : nodePredicateAnd (OR nodePredicateAnd)*;
nodePredicateAnd locals [SEQLEvaluable evaluable] :nodePredicateComparable (AND nodePredicateComparable)*;

nodePredicateComparable locals [SEQLEvaluable evaluable] 
        : NODEID (COMMA (STRING))?
        | ARCHETYPEID (COMMA (STRING))?
        | (COMMA (STRING))?
 		| predicateOperand (COMPARABLEOPERATOR predicateOperand);
 		
predicateOperand locals [SEQLEvaluable evaluable] 
        : objectPath | operand;



from    :    FROM EHR SYSTEM IDENTIFIER
		   | FROM EHR SYSTEM IDENTIFIER? CONTAINS containsExpr
		   | FROM EHR IDENTIFIER
		   | FROM EHR IDENTIFIER? CONTAINS containsExpr ;

 
containsExpr locals[ SEQLOperation operation]: containExpressionBool (boolOp containsExpr)?;

contains locals[SEQLOperation containsOperation] : simpleClassExpr (CONTAINS containsExpr)?;

allVersions : ALL VERSIONS OF;


containExpressionBool locals[ SEQLOperation operation] : contains | '(' containsExpr ')';
        

boolOp : AND | OR | XOR;


simpleClassExpr locals[SEQLFromComponent component]
        : allVersions? IDENTIFIER IDENTIFIER?  
        | archetypedClassExpr;
archetypedClassExpr
        : allVersions? IDENTIFIER IDENTIFIER? archetypePredicate; 
archetypePredicate
        : OPENBRACKET ARCHETYPEID CLOSEBRACKET;
        
where : WHERE identifiedExpr;
having: HAVING identifiedExpr;

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
        
operand locals[SEQLPrimitive primitive]: STRING | INTEGER | BOOLEAN | DATE | FLOAT ;

        