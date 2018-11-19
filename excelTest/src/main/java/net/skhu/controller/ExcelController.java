package net.skhu.controller;

import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.skhu.model.Person;
import net.skhu.service.ExcelService;

@Controller
public class ExcelController {

	@Autowired ExcelService excelService;

	@RequestMapping("excel/download")
	public void download(HttpServletResponse response) throws Exception {
		List<Person> persons = excelService.findAll();
		Workbook workbook = excelService.createXLS(persons);

		String fileName = URLEncoder.encode("엑셀.xls", "UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");

		try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
            workbook.write(output);
        }
	}

	@RequestMapping(value="/excel/upload", method=RequestMethod.GET)
    public String upload(Model model) {
		return "excel/upload";
    }

	@RequestMapping(value="/excel/upload", method=RequestMethod.POST)
    public String upload(Model model, @RequestParam("file") MultipartFile file) throws Exception {
        List<Person> persons = excelService.getPersons(file.getInputStream());
        model.addAttribute("persons", persons);
        System.out.println(persons.size());
        return "excel/upload";
    }

}
