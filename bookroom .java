import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BookRoom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String checkin = req.getParameter("checkin");
        String checkout = req.getParameter("checkout");
        String roomType = req.getParameter("roomType");

        PrintWriter out = res.getWriter();

        try {
            LocalDate in = LocalDate.parse(checkin);
            LocalDate outd = LocalDate.parse(checkout);

            if (outd.isAfter(in)) {
                out.println("<html><head><title>Booking Confirmed</title></head><body>");
                out.println("<h1>Booking Confirmed!</h1>");
                out.printf("<p>Thank you, %s. Here's your reservation:<br>", name);
                out.printf("Email: %s<br>Room: %s<br>", email, roomType);
                out.printf("Check-in: %s<br>Check-out: %s<br></p>", in, outd);
                out.println("</body></html>");
            } else {
                out.println("<html><body><h1>Error</h1><p>Check-out must be after check-in. Please go back.</p></body></html>");
            }
        } catch (DateTimeParseException e) {
            out.println("<html><body><h1>Error</h1><p>Invalid date. Please go back.</p></body></html>");
        } finally {
            out.close(); // Close the writer to release resources
        }
    }
}

