import java.util.*;

class EfficiencyStats {
    public static StatsResult computeStats(ArrayList<Car> carList) {
        List<Double> fuelEfficiencies = new ArrayList<>();
        for (Car car : carList) {
            fuelEfficiencies.add(car.getFuelEfficiency());
        }

        double mean = calculateMean(fuelEfficiencies);
        double median = calculateMedian(fuelEfficiencies);
        double mode = calculateMode(fuelEfficiencies);

        return new StatsResult(mean, median, mode);
    }

    private static double calculateMean(List<Double> fuelEfficiencies) {
        if (fuelEfficiencies.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (double efficiency : fuelEfficiencies) {
            sum += efficiency;
        }

        return sum / fuelEfficiencies.size();
    }

    private static double calculateMedian(List<Double> fuelEfficiencies) {
        Collections.sort(fuelEfficiencies);
        int size = fuelEfficiencies.size();

        if (size % 2 == 0) {
            // If the list has an even number of elements, take the average of the middle two.
            int middle = size / 2;
            return (fuelEfficiencies.get(middle - 1) + fuelEfficiencies.get(middle)) / 2.0;
        } else {
            // If the list has an odd number of elements, return the middle element.
            return fuelEfficiencies.get(size / 2);
        }
    }

    private static double calculateMode(List<Double> fuelEfficiencies) {
        Map<Double, Integer> frequencyMap = new HashMap<>();

        for (double efficiency : fuelEfficiencies) {
            frequencyMap.put(efficiency, frequencyMap.getOrDefault(efficiency, 0) + 1);
        }

        double mode = 0.0;
        int maxFrequency = 0;

        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mode = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        return mode;
    }
}

class StatsResult {
    double mean;
    double median;
    double mode;

    public StatsResult(double mean, double median, double mode) {
        this.mean = mean;
        this.median = median;
        this.mode = mode;
    }
}
