package CSCI5308.GroupFormationTool.Logger;

public class test {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
