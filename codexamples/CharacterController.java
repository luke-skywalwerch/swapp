package codexamples;

@Controller
public class CharacterController {

    // dependency injection for caracterservice
    @Autowired
    private CharacterService characterService;

    // endpoint route
    @PostMapping("/booking")

    // @requestBody defines that the endpoint is expecting an object inside the request body
    // ResponseEntity is the standard to wrap the return in an http request, inside the "<>" it is defined the actual object.
    // For simplification, it can be used "?" if the object is not typed or there are different types that can be returned
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest) {
        try {
            // the logic is made inside the service in the method createbooking
            bookingService.createBooking(bookingRequest);
            // if there is no error, an http 200 with a message is returned
            return ResponseEntity.ok("Booking created successfully");
        } catch (Exception e) {
            // if there is an error, an http 500 would be returned
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}