public class test {
    public static void main(String[] args) {
        String propName = "title";
      String setMethodName = "set" + propName.substring(0,1).toUpperCase()+propName.substring(1);
      // String setMethodName = "set" +propName.substring(1);
       // String setMethodName = "set" + propName.substring(0,1).toUpperCase();
        System.out.println(setMethodName);

    }

}
