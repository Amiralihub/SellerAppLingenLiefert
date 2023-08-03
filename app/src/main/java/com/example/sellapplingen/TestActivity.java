import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private TextView monthYearTextView;
    private GridView calendarGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        monthYearTextView = findViewById(R.id.monthYearTextView);
        calendarGridView = findViewById(R.id.calendarGridView);

        // Get the current date
        Calendar calendar = Calendar.getInstance();

        // Create a date format to display month and year
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        monthYearTextView.setText(sdf.format(calendar.getTime()));

        // Populate the calendar grid with dates
        ArrayList<String> datesList = new ArrayList<>();
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int day = 1; day <= daysInMonth; day++) {
            datesList.add(String.valueOf(day));
        }

        ArrayAdapter<String> calendarAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                datesList
        );

        calendarGridView.setAdapter(calendarAdapter);

        // Set item click listener to handle date selection
        calendarGridView.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedDate = (String) adapterView.getItemAtPosition(position);
            // Do something with the selected date
        });
    }
}
