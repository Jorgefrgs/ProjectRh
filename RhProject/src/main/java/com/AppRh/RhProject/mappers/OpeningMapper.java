package com.AppRh.RhProject.mappers;

import com.AppRh.RhProject.models.Opening;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OpeningMapper {

    public abstract Opening toOpening(Opening opening);

}
