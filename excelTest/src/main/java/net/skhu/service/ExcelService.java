package net.skhu.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import net.skhu.model.Person;

@Service
public class ExcelService {

	Person[] persons = new Person[] {
			new Person(1, "홍길동", new GregorianCalendar(1995, 3, 15).getTime(), 90.5, true),
			new Person(2, "임꺽정", new GregorianCalendar(1993, 5, 25).getTime(), 85.3, true),
			new Person(3, "전우치", new GregorianCalendar(1991, 7, 13).getTime(), 100.0, true)
	};

	public List<Person> findAll(){
		return Arrays.asList(persons);
	}

	CellStyle createDateStyle(Workbook workbook, String dateFormat) {
		CellStyle style = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		style.setDataFormat(createHelper.createDataFormat().getFormat(dateFormat));
		return style;
	}

	// 데이터베이스 목록의 데이터를 엑셀 workbook 객체에 채워서 리턴
	public Workbook createXLS(List<Person> persons) {
		HSSFWorkbook workbook = new HSSFWorkbook(); // 엑셀 2003 파일 객체를 생성, 엑셀 2007은 XSSF
		HSSFSheet sheet = workbook.createSheet();

		int rowCount = 0;
		Row row = sheet.createRow(rowCount++);
		row.createCell(0).setCellValue("ID");	// 컬럼 제목 행 생성
		row.createCell(1).setCellValue("이름");
		row.createCell(2).setCellValue("생일");
		row.createCell(3).setCellValue("점수");
		row.createCell(4).setCellValue("활성화");
		sheet.setColumnWidth(0, 5 * 256);	// 컬럼 넓이 설정 (최대 문자수 * 256)
		sheet.setColumnWidth(1, 10 * 256);
		sheet.setColumnWidth(2, 11 * 256);
		sheet.setColumnWidth(3, 7 * 256);
		sheet.setColumnWidth(3, 7 * 256);

		// 날짜 칸 포맷을 지정하기 위한 스타일 객체 생성
		CellStyle dateStyle = createDateStyle(workbook, "yyyy-MM-dd");

		for (Person person : persons) {
			row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(person.getId());
			row.createCell(1).setCellValue(person.getName());
			row.createCell(2).setCellValue(person.getBirthDate());
			row.createCell(3).setCellValue(person.getScore());
			row.createCell(4).setCellValue(person.isEnabled());

			// 날짜 포맷 지정
			row.getCell(2).setCellStyle(dateStyle);
		}
		return workbook;
	}

	// 업로드된 엑셀 파일에서 Person 객체 목록을 읽어서 리턴하는 메소드
	// 업로드된 엑셀 파일의 컬럼 순서는 다운로드된 엑셀 파일과 같아야 한다.
	public List<Person> getPersons(InputStream input) throws Exception {
		List<Person> persons = new ArrayList<>();
		// 업로드된 엑셀 파일을 읽기 위한 workbook 객체 생성
		Workbook workbook = WorkbookFactory.create(input);
		// 업로드된 파일의 첫번째 시트를 읽기 위한 sheet 객체 생성
		Sheet sheet = workbook.getSheetAt(0);

		for (int r = 1; r < sheet.getPhysicalNumberOfRows() ; ++r) {
			Row row = sheet.getRow(r);	// r번째 행의 데이터를 읽기 위한 row객체 생성
			if (row.getCell(0) == null) break;	// 데이터가 없으면 읽기를 종료
			int id = (int)row.getCell(0).getNumericCellValue();
			String name = row.getCell(1).getStringCellValue();
			Date birthDate = row.getCell(2).getDateCellValue();
			double score = row.getCell(3).getNumericCellValue();
			boolean enabled = row.getCell(4).getBooleanCellValue();
			// 읽은 데이터로 객체를 생성하여 목록에 추가
			persons.add(new Person(id, name, birthDate, score, enabled));
		}
		return persons;
	}

}
