package com.jobcandt.candidates.mappers;

import com.grpc.candidates.ConfirmationResponse;
import com.jobcandt.candidates.entities.Confirmation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationMapper {

  private ModelMapper modelMapper = new ModelMapper();

    public Confirmation fromGrpcConfirmation( ConfirmationResponse source){
        return  modelMapper.map(source,Confirmation.class );
    }
    public  ConfirmationResponse fromConfirmation(Confirmation source){
        return modelMapper.map(source, ConfirmationResponse.class);
    }

}
