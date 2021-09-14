import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class DioList{
    
    public static void main( String[] args ){
        Questao1.executar();
        Questao2.executar();
    }
}

class Questao1{

    public static void executar(){

        String [] months = { "1 - Janeiro", "2 - Fevereiro", "3 - Marco", "4 - Abril", "5 - Maio", "6 - Junho" };
        Float [] temps = { 25f, 23f, 24f, 31f, 29f, 27f };
    
        String description = """
                            Digita Inovation One - Bootcamp
                            Exercício Proposto no curso de Java - Trabalhando com Collections ( Lists )

                            Descricao: Este programa deve exibir a temperatura media mensal dos 6 primeiros
                            meses e informar a media total
        """;
        System.out.println( description );

        ArrayList<Temperature> avg =  Temperature.getTemps( months, temps );
        avg.forEach( temp -> System.out.println( String.format( "%s ºC", temp.toString() ) ) );

        float sum = 0;
        for( Temperature t : avg ) sum += t.getTemperature();

        System.out.println( String.format( "A media semestral foi: %s ºC", sum = sum/avg.size() ) );
    }
}

class Temperature{

    String month;
    Float temperature;

    Temperature(){}
    Temperature( String month, Float temperature ){
        this.month = month;
        this.temperature = temperature;
    }

    public String getMonth(){ return this.month; }
    public Float getTemperature(){ return this.temperature; }
    public void setMonth( String  month ) { this.month = month; }
    public void setTemperature( Float temperature ) { this.temperature = temperature; }

    public static ArrayList<Temperature> getTemps( String[] months, Float[] temps ){ 

        if( months.length != temps.length ) return new ArrayList<Temperature>() ;

        ArrayList<Temperature> tempArray = new ArrayList<Temperature>();

        for( int i = 0; i < months.length; i++ ) tempArray.add( new Temperature( months[i], temps[i] ) );

        return tempArray;
    }

    @Override
    public String toString(){
        return this.getMonth() + " " + this.getTemperature();
    }
}

class Questao2{

    public static void executar(){

        String description = """
                            Digita Inovation One - Bootcamp
                            Exercício Proposto no curso de Java - Trabalhando com Collections ( Lists )

                            Descricao: Este programa deve exibir um lista de interrgatorio e definir
                            se o usuario e: suspeita, cumplice, assassina ou inocente
        """;
        System.out.println( description );
        

        List<Interrogation> interrogations = List.of(
                                                new Interrogation( "Telefonou para a vitima ?", false ),
                                                new Interrogation( "Esteve no local do crime ?", false ),
                                                new Interrogation( "Mora perto da vitima ?", false ),
                                                new Interrogation( "Devia para a vitima ?", false ),
                                                new Interrogation( "Ja trabalhou com a vitima ?", false )
                                            );
        int results = Interrogation.runAndGetResults( interrogations.iterator() );

        switch ( results ) {
            case 0, 1 -> System.out.println( "Inocente" );
            case 2 -> System.out.println( "Supeito" );
            case 3, 4 -> System.out.println( "Cumplice" );
            default -> System.out.println( "Assassino" );
        };
    }
}

class Interrogation{

    String question;
    Boolean answer;

    public Interrogation( String question, Boolean answer ){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() { return question; }
    public void setQuestion( String question ) { this.question = question; }
    public Boolean getAnswer() { return answer; }
    public void setAnswer( Boolean answer ) { this.answer = answer; }
    public String toString(){ return this.getQuestion() + " " + getAnswer(); }

    public static int runAndGetResults( Iterator<Interrogation> questionIterator ){

        Boolean answer = false;
        Boolean checked = false;
        String answerToCheck = "test";
        Interrogation question = questionIterator.next();
        Integer count = 0;

        do{
            if( checked ) question = questionIterator.next();
            else System.out.println( "Answer 'true' or 'false':");

            System.out.println( question.getQuestion() );
            
            answerToCheck = System.console().readLine();
            
            checked = answerToCheck.equalsIgnoreCase( "true" ) || answerToCheck.equalsIgnoreCase( "false" );

            if( checked ) {
                answer = Boolean.parseBoolean( answerToCheck );
                question.setAnswer( answer );
                count = answer == true ? count + 1 : count;
            }
        }while( questionIterator.hasNext() );

        return count;
    }

}