import java.util.Calendar;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Agenda {
    private ArrayList<Calendar> datasAlugadas;
    private ArrayList<Calendar> datasBloqueadas;


    public Agenda() {
        this.datasAlugadas = new ArrayList<>();
        this.datasBloqueadas = new ArrayList<>();
    }

    public boolean adicionarDataAlugada(String dataString) {
        Calendar dataCalendar = null;

        try {
            dataCalendar = this.stringParaCalendar(dataString);
        } catch (Exception e) {
            System.out.println("Erro ao converter string para Calendar, utilize o padrão: dd/MM/yyyy: " + e.getMessage());
            return false;
        }

        if (this.dataEstaBloqueada(dataCalendar)) {
            System.out.println("A data está Bloqueada e não pode ser Alugada");
            return false;
        }

        System.out.println("A data teve seu estado alterado de Disponível para Alugada.");
        this.datasAlugadas.add(dataCalendar);
        return true;
    }

    public boolean adicionarDataBloqueada(String dataString) {
        Calendar dataCalendar = null;

        try {
            dataCalendar = this.stringParaCalendar(dataString);
        } catch (Exception e) {
            System.out.println("Erro ao converter string para Calendar, utilize o padrão: dd/MM/yyyy: " + e.getMessage());
            return false;
        }

        if (this.dataEstaAlugada(dataCalendar)) {
            System.out.println("A data já está Alugada e não pode ser Bloqueada");
            return false;
        }

        this.datasBloqueadas.add(dataCalendar);
        System.out.println("A data teve seu estado alterado de Disponível para Bloqueada.");
        return true;
    }

    private boolean dataEstaAlugada(Calendar data) {
        return this.datasAlugadas.contains(data);
    }

    public boolean dataEstaAlugada(String dataString) {
        Calendar dataCalendar = null;

        try {
            dataCalendar = this.stringParaCalendar(dataString);
        } catch (Exception e) {
            System.out.println("Erro de conversão de data, utilize o padrão: dd/MM/yyyy: ");
            return false;
        }

        return this.datasAlugadas.contains(dataCalendar);
    }

    private boolean dataEstaBloqueada(Calendar data) {
        return this.datasBloqueadas.contains(data);
    }

    public boolean dataEstaBloqueada(String dataString) {
        Calendar dataCalendar = null;

        try {
            dataCalendar = this.stringParaCalendar(dataString);
        } catch (Exception e) {
            System.out.println("Erro ao converter string para Calendar, utilize o padrão: dd/MM/yyyy: " + e.getMessage());
            return false;
        }

        return this.datasBloqueadas.contains(dataCalendar);
    }

    public boolean dataEstaDisponivel(String dataString) {
        Calendar dataCalendar = null;

        try {
            dataCalendar = this.stringParaCalendar(dataString);
        } catch (Exception e) {
            System.out.println("Erro ao converter string para Calendar, utilize o padrão: dd/MM/yyyy: " + e.getMessage());
            return false;
        }

        return (!this.datasAlugadas.contains(dataCalendar) && !this.datasBloqueadas.contains(dataCalendar));
    }

    public boolean removerDataAlugada(String data) {
        Calendar dataCalendar = null;

        try {
            dataCalendar = this.stringParaCalendar(data);
        } catch (Exception e) {
            System.out.println("Erro ao converter string para Calendar, utilize o padrão: dd/MM/yyyy: " + e.getMessage());
            return false;
        }

        if(this.dataEstaAlugada(dataCalendar)) {
            this.datasAlugadas.remove(dataCalendar);
            return true;
        }
        throw new IndexOutOfBoundsException("A data não está presente como alugada na agenda");
    }

    public boolean removerDataBloqueada(String data) {
        Calendar dataCalendar = null;

        try {
            dataCalendar = this.stringParaCalendar(data);
        } catch (Exception e) {
            System.out.println("Erro ao converter string para Calendar, utilize o padrão: dd/MM/yyyy: " + e.getMessage());
            return false;
        }

        if(this.dataEstaBloqueada(dataCalendar)) {
            this.datasBloqueadas.remove(dataCalendar);
            return true;
        }
        throw new IndexOutOfBoundsException("A data não está presente como alugada na bloqueada");
    }


    private static Calendar stringParaCalendar(String dataString) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(dataString));
        return calendar;
    }

    public ArrayList<Calendar> getDatasAlugadas() {
        return this.datasAlugadas;
    }

    public ArrayList<Calendar> getDatasBloqueadas() {
        return this.datasBloqueadas;
    }

    public void exibeDatasAlugadas() {
        if (this.datasAlugadas.isEmpty()) {
            System.out.println("Não existem datas Alugadas");
        } else {
            System.out.println("DATAS ALUGADAS:");
            for(Calendar data: this.datasAlugadas) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String dataString = formato.format(data.getTime());
                System.out.println(dataString);
            }
        }
    }

    public void exibeDatasBloqueadas() {
        if (this.datasBloqueadas.isEmpty()) {
            System.out.println("Não existem datas Bloqueadas");
        } else {
            System.out.println("DATAS BLOQUEADAS:");
            for(Calendar data: this.datasBloqueadas) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String dataString = formato.format(data.getTime());
                System.out.println(dataString);
            }
        }
    }

}
