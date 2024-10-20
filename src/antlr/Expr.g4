grammar Expr;

@header{
	package antlr;
}

p : 'prog' d* s* 'endprog' EOF		#Program
;

d : 'var' IDENT ';'		#Declaration
;

s : IDENT ':'		#Identlabel
  | IDENT ':=' e ';'		#Identvariable
  | 'input' IDENT ';'		#Input
  | 'output' e ';'		#Output
  | 'goto' IDENT ';'		#Goto
  | 'if' '(' e ')' 'goto' IDENT ';'		#Ifgoto
;

e : '(' e ')'		#Ebrackets
  | '-' e		#Enegative
  | e '*' e		#Emultiple
  | e '/' e		#Edivide
  | e '+' e		#Eadd
  | e '-' e		#Eminus
  | e '>' e		#Egreater
  | e '<' e		#Eless
  | e '=' e		#Eequal
  | IDENT		#Ident
  | NUMBER		#Num
;


NUMBER : [0-9]+ 
;

IDENT : [a-zA-Z_][a-zA-Z0-9_]*
;

COMMENT : '//' ~[\r\n]* -> skip
;

WS : [ \t\n\r]+ -> skip
;