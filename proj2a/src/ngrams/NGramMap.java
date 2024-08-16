package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    int sizeOfName;
    String[] name;
    TimeSeries[] ts;
    Double[][] alldatas;
    int sizeofAllDatas;
    // TODO: Add any necessary static/instance variables.

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        //sizeOfName = 3 / 4
        //     0     1     2     3
        // name0 name1 name2 name3
        // ts  0     1     2     3
        //sizeOfTs = ?
        sizeOfName = -1;
        name = new String[10];
        Arrays.fill(name, "0234");
        ts = new TimeSeries[10];
        In in1 = new In(wordsFilename);
        while(in1.hasNextLine()){
            String line = in1.readLine();
            String[] words = line.split("\t");
            //两个数组（没有初始化），开始读入的时候为null，需要赋值为words的first value
            //读入新的单词时，sizeOfName会停留在上一个单词里 加上则导致新单词的第一个年份漏掉
            if(sizeOfName == -1 || !name[sizeOfName].equals(words[0])){
                sizeOfName ++;
                resizeOfName();
                ts[sizeOfName] = new TimeSeries();
                name[sizeOfName] = words[0];
                ts[sizeOfName].put(Integer.parseInt(words[1]), Double.parseDouble(words[2]));
            }
            else{
                ts[sizeOfName].put(Integer.parseInt(words[1]), Double.parseDouble(words[2]));
           }
        }
        //alldatas's first number is year, The second is the total number of words recorded from all texts that year
        In in2 = new In(countsFilename);
        alldatas = new Double[sizeOfName][2];
        while(in2.hasNextLine()) {
            String line = in2.readLine();
            String[] tokens = line.split(",");
            if(Integer.parseInt(tokens[0]) < MIN_YEAR) {
                continue;
            }
            if(Integer.parseInt(tokens[0]) > MAX_YEAR) {
                break;
            }
            resize();
            alldatas[sizeofAllDatas][0] = Double.parseDouble(tokens[0]);
            alldatas[sizeofAllDatas][1] = Double.parseDouble(tokens[1]);
            sizeofAllDatas ++;
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    //N
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        for(int i = 0; i < name.length; i++){
            if(name[i].equals(word)){
                TimeSeries newTs = new TimeSeries(ts[i], startYear, endYear);
                return newTs;
            }
        }
        return new TimeSeries();
    }


    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    //N
    public TimeSeries countHistory(String word) {
        for(int i = 0; i < name.length; i++){
            if(name[i].equals(word)){
                int startYear = ts[i].firstKey();
                int endYear = ts[i].lastKey();
                TimeSeries newTs = new TimeSeries(ts[i], startYear, endYear);
                return newTs;
            }
        }
        return new TimeSeries();
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    //N
    public TimeSeries totalCountHistory() {
        TimeSeries totalCountHistory = new TimeSeries();
        for (Double[] ints : alldatas) {
            totalCountHistory.put(Integer.valueOf(String.valueOf(ints[0])), ints[1]);
        }
        return totalCountHistory;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */

    //N
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        for(int i = 0; i < name.length; i++){
            if(name[i].equals(word)){
                TimeSeries newTs = new TimeSeries(ts[i], startYear, endYear);
                List<Integer> year = newTs.years();
                int yearAnchor = 0;
                List<Double> data = newTs.data();
                for(int j = 0; j < alldatas.length; j++){
                    if(alldatas[j][0] == (double) year.get(yearAnchor) && alldatas[j][0] >= startYear && alldatas[j][0] <= endYear){
                        newTs.replace((int) Math.floor(alldatas[j][0]), data.get(yearAnchor) / alldatas[j][1]);
                        yearAnchor++;
                    }
                }
            }
        }
        return new TimeSeries();
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        for(int i = 0;i < name.length;i++){

        }
        return new TimeSeries();
    }


    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        return null;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return null;
    }



    private void resize(){
        if(sizeofAllDatas == alldatas.length){
            Double[][] newalldatas = new Double[alldatas.length * 2][2];
            for(int i = 0; i < alldatas.length; i++){
                System.arraycopy(alldatas[i], 0, newalldatas[i], 0, 2);
            }
            alldatas = newalldatas;
       }
    }
    private void resizeOfName(){
        if(sizeOfName + 1 == name.length){
            String[] newName = new String[name.length * 2];
            Arrays.fill(newName, "0123");
            TimeSeries[] newts = new TimeSeries[ts.length * 2];
            System.arraycopy(name, 0, newName, 0, name.length);
            System.arraycopy(ts, 0, newts, 0, ts.length);
            name = newName;
            ts = newts;
        }
    }


    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
