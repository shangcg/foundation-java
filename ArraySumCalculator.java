public class ArraySumCalculator {
    /**
     * 计算整数数组的和
     * @param numbers 整数数组
     * @return 数组元素之和
     */
    public static int sum(int[] numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }
}
