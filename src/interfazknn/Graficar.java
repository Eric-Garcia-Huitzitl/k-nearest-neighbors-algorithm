package interfazknn;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Log;
import org.jfree.util.LogContext;

public class Graficar extends ApplicationFrame {
    String [][]valores;
    private static final LogContext LOGGER = Log.createContext(Graficar.class);
//Le debemos pasar el numero de clases
    public Graficar(final String title,String [][] valores) {
        super(title);
        this.valores=valores;
        final BoxAndWhiskerCategoryDataset dataset = createSampleDataset();
        final CategoryAxis xAxis = new CategoryAxis("Clases");
        final NumberAxis yAxis = new NumberAxis("Numero de Instancias");
        yAxis.setAutoRangeIncludesZero(false);
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(false);
        renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
        final JFreeChart chart = new JFreeChart(
            "Gráfica de Caja",
            new Font("SansSerif", Font.BOLD, 16),
            plot,
            true
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 270));
        setContentPane(chartPanel);
        }
    public String [] getNombreClases(String [][] ConjuntoP){
		String [] arre=new String[ConjuntoP.length];
		String [] clases;
                for (int i=0;i<ConjuntoP.length ;i++ ) {
			arre[i]=ConjuntoP[i][ConjuntoP[0].length-1];
		}
		for(int i=0;i<arre.length;i++){
			for(int j=0;j<arre.length-1;j++){
				if(i!=j){
					if(arre[i].equals(arre[j])){
						arre[i]="";
					}
				}
			}
		}

int n=arre.length;
int contador=0;
		for (int k=0;k<=n-1;k++){
			if(arre[k]!=""){
				contador++;
			}
		}
clases=new String[contador];
int conta=0;
for (int k=0;k<=n-1;k++){
			if(arre[k]!=""){
			clases[conta]=arre[k];
			System.out.println(clases[conta]);
			conta++;
			}
		}
return clases;
}
    private BoxAndWhiskerCategoryDataset createSampleDataset() {
        String clasesDef[]=getNombreClases(valores);
        int x=0,contador=0;
        final int seriesCount = clasesDef.length; // 2 clases 
        final int categoryCount = 1; //numero de cajas        
        final int entityCount = valores.length;
        int aux=0;
        final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        for (int i = 0; i < 1; i++) { //NUmero de clases
            for (int j = 0; j < seriesCount; j++) { //NUmero de cajas 
                 final List list = new ArrayList(); //Crea una lista              
                for (int k = 0; k < valores.length; k++) {  
                    if(clasesDef[aux].equals(valores[k][valores[0].length-1])){
                    list.add(new Double(Double.parseDouble(valores[k][0])));
                    }
                } 
                LOGGER.debug("Atributo " + contador);
                LOGGER.debug(list.toString());
                dataset.add(list, "Clase: " + clasesDef[aux], " Clase:  " +clasesDef[aux]);
                x++;
            aux++;
            }
        }
        return dataset;
    }
}