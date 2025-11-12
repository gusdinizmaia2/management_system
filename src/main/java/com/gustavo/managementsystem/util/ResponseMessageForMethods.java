// package com.gustavo.managementsystem.util;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.context.annotation.Bean;
// import org.springframework.stereotype.Component;

// import springfox.documentation.builders.ResponseMessageBuilder;
// import springfox.documentation.schema.ModelRef;
// import springfox.documentation.service.ResponseMessage;

// @Component
// public class ResponseMessageForMethods {

//     @Bean
//     public List<ResponseMessage> responseMessageForGET(){

//         return new ArrayList<ResponseMessage>() {{
//             add(new ResponseMessageBuilder()
//                 .code(500)
//                 .message("500 message")
//                 .responseModel(new ModelRef("Error"))
//                 .build());
//             add(new ResponseMessageBuilder()
//                 .code(403)
//                 .message("Forbidden!")
//                 .build());
//         }};
// }
    
// }
