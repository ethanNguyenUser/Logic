import java.util.function.BiFunction;

class Operation{
    private String operator; //For LogicalSentences, it could be &, |, ~, =>, and <=>
    private BiFunction<Boolean, Boolean, Boolean> function; //The function that does the actual evaluating

    public Operation(String operator, BiFunction<Boolean, Boolean, Boolean> function){
        this.operator = operator;
        this.function = function;
    }
    public String getOperator(){
        return operator;
    }
    public BiFunction<Boolean, Boolean, Boolean> getFunction(){
        return function;
    }
}