package com.example.lichamcuoikyjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import net.objecthunter.exp4j.ExpressionBuilder;


public class btcn extends Activity {

    public EditText nhap;
    public double kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btcn);

        Button khong = findViewById(R.id.khong);
        Button mot = findViewById(R.id.mot);
        Button hai = findViewById(R.id.hai);
        Button ba = findViewById(R.id.ba);
        Button bon = findViewById(R.id.bon);
        Button nam = findViewById(R.id.nam);
        Button sau = findViewById(R.id.sau);
        Button bay = findViewById(R.id.bay);
        Button tam = findViewById(R.id.tam);
        Button chin = findViewById(R.id.chin);
        Button cong = findViewById(R.id.cong);
        Button tru = findViewById(R.id.tru);
        Button nhan = findViewById(R.id.nhan);
        Button chia = findViewById(R.id.chia);
        Button cham = findViewById(R.id.cham);
        Button bang = findViewById(R.id.bang);
        Button xoa = findViewById(R.id.xoa);

        nhap = findViewById(R.id.nhap);

    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public void casio(View view){
        String[] chuoiHienCo = new String[]{nhap.getText().toString()};
        switch (view.getId()){
            case R.id.khong:

                nhap.setText(chuoiHienCo[0]+"0");
                break;
            case R.id.mot:

                nhap.setText(chuoiHienCo[0]+"1");
                break;
            case R.id.hai:

                nhap.setText(chuoiHienCo[0]+"2");
                break;
            case R.id.ba:

                nhap.setText(chuoiHienCo[0]+"3");
                break;
            case R.id.bon:

                nhap.setText(chuoiHienCo[0]+"4");
                break;
            case R.id.nam:

                nhap.setText(chuoiHienCo[0]+"5");
                break;
            case R.id.sau:

                nhap.setText(chuoiHienCo[0]+"6");
                break;
            case R.id.bay:

                nhap.setText(chuoiHienCo[0]+"7");
                break;
            case R.id.tam:

                nhap.setText(chuoiHienCo[0]+"8");
                break;
            case R.id.chin:

                nhap.setText(chuoiHienCo[0]+"9");
                break;
            case R.id.cham:

                nhap.setText(chuoiHienCo[0]+".");
                break;
            case R.id.cong:
                nhap.setText(chuoiHienCo[0]+"+");
                break;
            case R.id.tru:
                nhap.setText(chuoiHienCo[0]+"-");
                break;
            case R.id.nhan:
                nhap.setText(chuoiHienCo[0]+"*");
                break;
            case R.id.chia:
                nhap.setText(chuoiHienCo[0]+"/");
                break;
            case R.id.xoaedt:
                nhap.setText(null);
                break;
            case R.id.bang:
                kq= eval(chuoiHienCo[0]);
                nhap.setText(String.valueOf(kq));
                break;
        }
    }
}
