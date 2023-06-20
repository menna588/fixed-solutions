package Helpers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.shaft.api.RestActions;

import io.restassured.response.Response;

public class Generators {

	public static String generateRandomWord(int len) {
		// This method to generate random words that can
		// be used in description fields or title
		// field and let us know how many characters
		// generated that can help in fields limit validations

		String name = "";
		for (int i = 0; i < len; i++) {
			int v = 1 + (int) (Math.random() * 26);
			char c = (char) (v + (i == 0 ? 'A' : 'a') - 1);
			name += c;
		}
		return name;
	}

	public static String generateRandomHexColor() {
		// This method to generate random color in Hex format
		Random randNum = new Random();
		int hexCode = randNum.nextInt(0xffffff + 1);
		String color = String.format("#%06x", hexCode);
		return color;
	}
	public static String getCurrentDayOfMonth(){
		LocalDate currentDate = LocalDate.now();
		StringBuilder currentDay = new StringBuilder();
		currentDay.append(currentDate.getDayOfMonth());
		String day = currentDay.toString();
		return day;
	}
	public static String getCurrentDayOfMonthSubstractedBy(int days){
		LocalDate currentDate = LocalDate.now().minusDays(days);
		StringBuilder currentDay = new StringBuilder();
		currentDay.append(currentDate.getDayOfMonth());
		String day = currentDay.toString();
		return day;
	}
	public static String generateRandomCalenderDate() {
		// This method to retun today's date in the below specific pattern
		// So it can be used in meetings or groups creation (start - end) dates
		String pattern = "MMM dd, yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		return date;
	}

	// Extracts list of path in response then converts result to string
	public static String getReturnedDataValue(Response response, String path) {
		String responseData = RestActions.getResponseJSONValue(response, path).replace("[", "").replace("]", "")
				.replace(",", " ");

		return responseData;
	}

	// Variable wrapper to add "input" in variables in request

	// return current date in format yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	public static String currentDate() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(OffsetDateTime.now());
	}

	// Helper method to return list of values from response
	public static String[] getListValuesFromResponse(String path, Response response) {
		String[] responseData = RestActions.getResponseJSONValue(response, path).replace("[", "").replace("]", "")
				.split(",", -1);

		if (Arrays.toString(responseData).equals("[]")) {
			return new String[] {};
		}

		return responseData;
	}

	// To return number of meetings returned in response.
	public static int getResponseNodesNumber(String path, Response response) {
		return getListValuesFromResponse(path, response).length;
	}

	// to match given string with given regex.
	public static boolean match(String regex, String data) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(data).find();
	}

	// Constructing regular expression contains all words in array list(Anding).
	public static String constructRegexWithMultipleStringsAnding(ArrayList<String> filterParameterValues) {
		String regex = "";
		for (int i = 0; i < filterParameterValues.size(); i++) {
			if (i > 0)
				regex += '|';
			regex += filterParameterValues.get(i);
		}
		return regex;
	}

	// Constructing regular expression contains all words in array list (oring).
	public static String constructRegexWithMultipleStringsOring(ArrayList<String> filterParameterValues) {
		String regex = "";
		for (int i = 0; i < filterParameterValues.size(); i++) {
			if (i > 0)
				regex += '|';
			regex += filterParameterValues.get(i);
		}
		return regex;
	}

	// format yyyy-MM-dd'T'HH:mm:ss.SSSZ to yyyy-mm-dd HH:mm:ss
	public static DateTime convertDateFormate(String date) {
		OffsetDateTime oldFormat = OffsetDateTime.parse(date);
		OffsetDateTime newFormat = oldFormat.truncatedTo(ChronoUnit.SECONDS);
		String newDate = newFormat.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + 'Z';

		org.joda.time.format.DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis();
		DateTime localDateTime = dateTimeFormatter.parseDateTime(newDate);

		return localDateTime;
	}


	public static String setDatePlus(int months, int weeks, int days, int hours, int minutes, int seconds) {
		String date = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
				.format(OffsetDateTime.now().plusMonths(months).plusWeeks(weeks).plusDays(days).plusHours(hours)
						.plusMinutes(minutes).plusSeconds(seconds));
		return date;
	}

	public static String setDateMinus(int months, int weeks, int days, int hours, int minutes, int seconds) {
		String date = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
				.format(OffsetDateTime.now().minusMonths(months).minusWeeks(weeks).minusDays(days).minusHours(hours)
						.minusMinutes(minutes).minusSeconds(seconds));
		return date;
	}

}
