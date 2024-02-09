package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import database.KhachHangDAO;
import model.KhachHang;

@WebServlet("/khach-hang-thay-doi-anh")
public class KhachHangThayDoiAnh extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public KhachHangThayDoiAnh() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("123");
		Object obj = request.getSession().getAttribute("khachHang");
		KhachHang khachHang = null;
		if (obj != null) {
			khachHang = (KhachHang) obj;
		}
		if (khachHang != null) {
			try {
				String folderPath = getServletContext().getRealPath("avatar");
				System.out.println(folderPath);
				File folder = new File(folderPath);
				if (!folder.exists()) {
					folder.mkdirs(); // create the folder if it doesn't exist
				}

				int maxFileSize = 5000 * 1024;
				int maxMeSize = 5000 * 1024;

				String contentType = request.getContentType();

				if (contentType != null && contentType.indexOf("multipart/form-data") >= 0) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(maxMeSize);

					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setSizeMax(maxFileSize);

					List<FileItem> files = upload.parseRequest(request);

					for (FileItem fileItem : files) {
						if (!fileItem.isFormField()) {
							String fileName = System.currentTimeMillis() + fileItem.getName();
							String filePath = folderPath + File.separator + fileName;
							File uploadedFile = new File(filePath);

							fileItem.write(uploadedFile);

							khachHang.setDuongDanAnh(fileName);
							KhachHangDAO khachHangDAO = new KhachHangDAO();
							khachHangDAO.updateImage(khachHang);
							khachHang = khachHangDAO.selectById(khachHang);
						} else {
							String name = fileItem.getFieldName();
							String value = fileItem.getString();
							System.out.println(name + " : " + value);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
