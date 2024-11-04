public class Main {
    public static void main(String[] args) {
        Date date = new Date(2020, 11, 30);
        System.out.println("Bieżąca data: " + date.getCurrentDate());
        date.moveForwardOneWeek();
        System.out.println("Data po przesunięciu o jeden tydzień w przód: " + date.getCurrentDate());
        date.moveBackwardOneWeek();
        date.moveBackwardOneWeek();
        System.out.println("Data po przesunięciu o jeden tydzień w tył: " + date.getCurrentDate());

        System.out.println("Dzień tygodnia za pomocą metody porównania: " + date.dayOfWeekUsingComparison());
        System.out.println("Dzień tygodnia za pomocą algorytmu Zellera: " + date.dayOfWeekUsingZeller());

        System.out.println("Pełna data z dniem tygodnia: " + date.formatDate("fullWithDay"));
        System.out.println("Pełna data: " + date.formatDate("full"));
        System.out.println("Data w formacie numerycznym: " + date.formatDate("numeric"));
        System.out.println("Skrócona data z dniem tygodnia: " + date.formatDate("shortWithDay"));

        Date dateFromString = new Date("2021", "12", "01");
        System.out.println("Data utworzona z String: " + dateFromString.getCurrentDate());

        Date parsedDate = Date.parse("12-01-2021");
        System.out.println("Data sparsowana: " + parsedDate.getCurrentDate());
    }
}
