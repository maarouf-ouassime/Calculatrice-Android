package com.example.calculatrice;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private TextView calculatrice , resultat;
    private String curr,res;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,
            btn8,btn9,btnAC,btnDEL,btnDivision,btnMultiplication,
            btnSoustraction,btnDot,btnEqual,btnAddition;
    private boolean dot_exist , operator_exist ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatrice = (TextView) findViewById(R.id.Calculatrise);
        resultat = (TextView) findViewById(R.id.resultat);
        res = "";
        curr = "";
        dot_exist = false;
        operator_exist = false;
        btn0 = (Button) findViewById(R.id.btn0);btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);btn9 = (Button) findViewById(R.id.btn9);
        btnAC = (Button) findViewById(R.id.btnAC);btnDEL = (Button) findViewById(R.id.btnDEL);
        btnDivision = (Button) findViewById(R.id.btnDivision);
        btnMultiplication = (Button) findViewById(R.id.btnMultiplication);
        btnSoustraction = (Button) findViewById(R.id.btnSoustraction);
        btnDot = (Button) findViewById(R.id.btnDot);btnEqual = (Button) findViewById(R.id.btnEqual);
        btnAddition = (Button) findViewById(R.id.btnAddition);

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // si l'ecran vide => afficher 0. et dot_exist devient true
                if(curr.isEmpty()){
                    curr = "0.";
                    dot_exist = true;
                }
                //si dot_unserted == false alors afficher .
                if(dot_exist == false){
                    curr = curr + ".";
                    dot_exist = true;
                }
                afficherCurr();
            }
        });
    }
    public void supprimer(View view){
        if(!curr.isEmpty()){
            // verifier si le point est le dernier caracter donc dot_exist =false
            if(curr.substring(curr.length()-1,curr.length()).equals(".")){
                dot_exist = false;
            }
            // si operator detecter supprimer 3 caracters et operator_exist = false
            if(curr.substring(curr.length()-1,curr.length()).equals(" ")){
                curr = curr.substring(0,curr.length()-3);
                operator_exist = false;
            }else {
                // si curr ne pas vide => supprimer le dernier chiffre
                curr = curr.substring(0,curr.length()-1);
            }
            afficherCurr();
        }
    }
    public void afficherCurr(){
        calculatrice.setText(curr);
    }
    public void afficherRes(){
        resultat.setText(res);
    }
    public void ajouterChiffre(View view){
        String val = ((Button)view).getText().toString();
        curr = curr +val;
        afficherCurr();
    }
    public void afficherOprtator(View view){
        String op = ((Button)view).getText().toString();
        op.trim();
        dot_exist = false ;
        if(!curr.isEmpty()){
            //verifier si le dernier caractere est . => la supprimer
            if(curr.substring(curr.length()-1,curr.length()).equals(".")){
                supprimer(view);
            }
            // verifier si operator_exist == false => concatener l'operator avec curr
            if(operator_exist == false){
                curr = curr +" "+op+" ";
                operator_exist = true;
            }
        }
    }
    public void supprimerTous(View view){
        curr ="";
        res ="";
        dot_exist = false;
        operator_exist = false;
        afficherCurr();
        afficherRes();
    }
    public void egale(View view) {
        // verifier si le dernier caractere ne pas un operator
        if(operator_exist==true && !curr.substring(curr.length()-1,curr.length()).equals(" ")){
            String caracters[] = curr.split(" ");
            switch (caracters[1].charAt(0)){
                case '+':
                    res = Double.toString(Double.parseDouble(caracters[0]) + Double.parseDouble(caracters[2]));
                    break;
                case '-':
                    res = Double.toString(Double.parseDouble(caracters[0]) - Double.parseDouble(caracters[2]));
                    break;
                case '*':
                    res = Double.toString(Double.parseDouble(caracters[0]) * Double.parseDouble(caracters[2]));
                    break;
                case '/':
                    res = Double.toString(Double.parseDouble(caracters[0]) / Double.parseDouble(caracters[2]));
                    break;
            }
            afficherRes();
        }
    }
}