import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }
    @Test
    public void divideTest(){
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1994, 200.0);
        catPopulation.put(1995, 400.0);
        catPopulation.put(1996, 500.0);
        catPopulation.put(1997, 500.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);
        dogPopulation.put(1996, 600.0);
        dogPopulation.put(1997, 700.0);

        TimeSeries totalPopulation = catPopulation.dividedBy(dogPopulation);
        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1994, 1995, 1997));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedData = Arrays.asList(0.5, 0.8, 0.7142857142857143);
        for (int i = 0; i < totalPopulation.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedData.get(i));
        }
    }
    @Test
    public void timeSeriesTest(){
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1994, 200.0);
        catPopulation.put(1995, 400.0);
        catPopulation.put(1996, 500.0);
        catPopulation.put(1997, 500.0);
        TimeSeries dogPopulation = new TimeSeries(catPopulation,1994,1997);

        TimeSeries gg = new TimeSeries(catPopulation,1995,1998);
        List<Integer> expectedYear = new ArrayList<>
                (Arrays.asList(1995, 1996, 1997));
        List<Double> expectedDatas = new ArrayList<>
                (Arrays.asList(400.0,500.0,500.0));
        assertThat(gg.data()).isEqualTo(expectedDatas);
        assertThat(gg.years()).isEqualTo(expectedYear);
        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1994, 1995, 1996, 1997));
        assertThat(dogPopulation.years()).isEqualTo(expectedYears);
        List<Double> expectedData = new ArrayList<>
                (Arrays.asList(200.0,400.0,500.0,500.0));
        assertThat(dogPopulation.data()).isEqualTo(expectedData);
    }
} 