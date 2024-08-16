package ngrams;

import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        if(!valid(ts) || startYear > endYear) {
            return;
        }
        startYear = Math.max(startYear, MIN_YEAR);
        endYear = Math.min(endYear, MAX_YEAR);
        for(int i = startYear; i <= endYear; i++){
            if(ts.containsKey(i)){
                this.put(i, ts.get(i));
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    //从小到大
    public List<Integer> years() {
        // TODO: Fill in this method.
        List<Integer> years = new ArrayList<>();
        if(!valid(this)){
            return years;
        }
        int[] temp = this.lowAndUpper();
        for(int i = temp[0]; i <= temp[1]; i++){
            if(this.containsKey(i)){
                years.add(i);
            }
        }
        return years;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Double> data = new ArrayList<>();
        if(!valid(this)){
            return data;
        }
        int[] temp = this.lowAndUpper();
        for(int i = temp[0]; i <= temp[1]; i++){
           if(this.containsKey(i)){
               data.add(this.get(i));
           }
        }
        return data;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries newts = new TimeSeries();
        if(!valid(ts) && !valid(newts)){
            return newts;
        }
        int[] temp = this.lowAndUpper();
        int[] temp0 = ts.lowAndUpper();
        int min = Math.max(Math.min(temp[0], temp0[0]),MIN_YEAR);
        int max = Math.min(Math.max(temp[1], temp0[1]),MAX_YEAR);
        for(int i = min; i <= max; i++){
            if(!this.containsKey(i) && !ts.containsKey(i)){
            }
            else if(this.containsKey(i) && ts.containsKey(i)){
                newts.put(i, this.get(i) + ts.get(i));
            }
            else if(this.containsKey(i)){
                newts.put(i, this.get(i));
            }
            else{
                newts.put(i, ts.get(i));
            }
        }
        return newts;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        if(!valid(ts)){
            return null;
        }
        TimeSeries newts = new TimeSeries();
        int[] temp = this.lowAndUpper();
        int[] temp0 = ts.lowAndUpper();
        int min = Math.max(Math.min(temp[0], temp0[0]),MIN_YEAR);
        int max = Math.min(Math.max(temp[1], temp0[1]),MAX_YEAR);
        for(int i = min; i <= max; i++){
            if(!this.containsKey(i)){
                continue;
            }
            else if(!ts.containsKey(i)){
                throw new IllegalArgumentException();
            }
            newts.put(i, this.get(i) / ts.get(i));
        }
        return newts;
    }

    //得到第一个值，得到最后一个值，
    private int[] lowAndUpper(){
        int[] lowAndUpper = new int[2];
        lowAndUpper[0] = firstKey();
        lowAndUpper[1] = lastKey();
        return lowAndUpper;
    }
    private boolean valid(TimeSeries ts){
        if(ts.size() == 0){
           return false;
        }
        return true;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
