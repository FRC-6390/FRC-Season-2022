public class Test{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);  

        int num1, num2; double dnum1, dnum2;  

        System.out.print("Enter an integer: ");  

        num1 = keyboard.nextInt();  

        System.out.print("Enter a double value: ");  

        dnum1 = keyboard.nextDouble();  

        System.out.print("\nEnter an integer: ");  

        num2 = keyboard.nextInt();  

        System.out.print("Enter a double value: ");  

        dnum2 = keyboard.nextDouble();  

        System.out.println();  

        System.out.print(num1 + dnum1);  

        System.out.println(" is an approximation of PI");  

        System.out.print(num2 - dnum2);  

        System.out.println(" is also an approximation of PI"); 
    }
}