/**
 * Created by yue on 5/3/2015.
 */
public class NaiveSearch {

    public static int searching(String search,String target){
        for(int i=0; i<search.length()-target.length()+1; i++){
            if(search.charAt(i) == target.charAt(0)){
                if(match(search, target, i)){
                    return i;
                }
            }
        }
        return -1;
    }

    public static boolean match(String search, String target, int startIdx){
        for(int i=startIdx, j=0; j<target.length(); i++,j++){
            if(search.charAt(i) != target.charAt(j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String search = "testtestettesting";
        String target = "testing";
        System.out.print(searching(search, target));
    }
}
