package com.andy.algs4.p1_fundamentals.examples;



import edu.princeton.cs.algs4.p1_fundamentals.p3_bags_queues_stacks.Stack;
import edu.princeton.cs.algs4.utils.StdIn;
import edu.princeton.cs.algs4.utils.StdOut;


/**
 * <p>Evaluates (fully parenthesized) arithmetic expressions using Dijkstra's two-stack algorithm.</p>
 *
 * <p>% java Evaluate</p>
 * <p>( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )</p>
 * <p>101.0</p>
 *
 * <p>% java Evaulate</p>
 * <p>( ( 1 + sqrt ( 5 ) ) / 2.0 )</p>
 * <p>1.618033988749895</p>
 *
 * <p>Note: the operators, operands, and parentheses must be separated by whitespace. Also, each operation must be enclosed in parentheses. For example, you must write ( 1 + ( 2 + 3 ) ) instead of ( 1 + 2 + 3 ). See EvaluateDeluxe.java for a fancier version.</p>
 *
 * <p>Remarkably, Dijkstra's algorithm computes the same answer if we put each operator *after* its two operands instead of *between* them.</p>
 *
 * <p>% java Evaluate</p>
 * <p>( 1 ( ( 2 3 + ) ( 4 5 * ) * ) + )</p>
 * <p>101.0</p>
 *
 * <p>Moreover, in such expressions, all parentheses are redundant! Removing them yields an expression known as a postfix expression. 1 2 3 + 4 5 * * +</p>
 */

public class Evaluate {
    public static void main(String[] args) {
        Stack<String> operators = new Stack<>();
        Stack<Double> values = new Stack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            switch (s) {
                case "(" -> {}
                case "+", "-", "/", "*", "sqrt" -> operators.push(s);
                case ")" -> {
                    String op = operators.pop();
                    double v = values.pop();
                    v = switch (op) {
                        case "+" -> values.pop() + v;
                        case "-" -> values.pop() - v;
                        case "*" -> values.pop() * v;
                        case "/" -> values.pop() / v;
                        case "sqrt" -> Math.sqrt(v);
                        default -> v;
                    };
                    values.push(v);
                }
                default -> values.push(Double.parseDouble(s));
            }
        }
        StdOut.println(values.pop());

    }
}
