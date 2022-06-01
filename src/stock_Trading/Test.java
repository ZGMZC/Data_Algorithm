package stock_Trading;

public class Test {
    @org.junit.Test
    public void test(){
        stockTrading stockTrading=new stockTrading();
        System.out.println(stockTrading.maxProfit_One(new int[]{7,1,5,3,6,4}));
        System.out.println(stockTrading.maxProfit_MAX(new int[]{7,1,5,3,6,4}));
        System.out.println(stockTrading.maxProfit_Cooldown(new int[]{7,1,5,3,6,4}));
        System.out.println(stockTrading.maxProfit_Fee(new int[]{7,1,5,3,6,4},2));
        System.out.println(stockTrading.maxProfit_Two(new int[]{5,7,2,7,3,3,5,3,0}));
        System.out.println(stockTrading.maxProfit_Any(new int[]{5,7,2,7,3,3,5,3,0},4));
    }
}
