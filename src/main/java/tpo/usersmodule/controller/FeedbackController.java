package tpo.usersmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tpo.usersmodule.controller.dtos.FeedbackDTO;

import tpo.usersmodule.model.entity.Feedback;
import tpo.usersmodule.service.IFeedbackService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;
    
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/feedbacks")
    public ResponseEntity<?> getFeedbacks() {
        try {
            List<Feedback> nots = feedbackService.findAll();
            List<FeedbackDTO> dtos = convertirFeedbacksADTO(nots);
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<?> getFeedback(@PathVariable int id) {
        try {
            Feedback f = feedbackService.findById(id);
            FeedbackDTO dto = new FeedbackDTO(f);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/feedbacks/{dni}")
    public ResponseEntity<?> getFeedbacksByUser(@PathVariable int dni) {
        try {
            List<Feedback> feedbacks = feedbackService.findByDni(dni);
            List<FeedbackDTO> dtos = convertirFeedbacksADTO(feedbacks);
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @CrossOrigin
    @PostMapping("/feedbacks/{dni}")
    public ResponseEntity<?> addFeedback(@PathVariable int dni,@RequestBody Feedback f) {
        String msj = "";

        try {
            feedbackService.save(dni, f);
            msj = "Feedback guardado exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }


    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/admin/feedbacks/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable int id) {
        String msj;
        try {
            feedbackService.deleteById(id);
            msj = "Feedback eliminado correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }


    private List<FeedbackDTO> convertirFeedbacksADTO(List<Feedback> feedbacks) {
        List<FeedbackDTO> dtos = new ArrayList<FeedbackDTO>();
        if (feedbacks != null) {
            for (Feedback feedback: feedbacks) {
                dtos.add(new FeedbackDTO(feedback));
            }
        }
        return dtos;
    }
    
    
}
