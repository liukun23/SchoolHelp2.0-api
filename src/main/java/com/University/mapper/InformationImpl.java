package com.University.mapper;

import com.University.model.Information;

import java.util.List;

public interface InformationImpl {
    List<Information> selectInformation();
    Information selectInformationById(Integer informationId);
}
