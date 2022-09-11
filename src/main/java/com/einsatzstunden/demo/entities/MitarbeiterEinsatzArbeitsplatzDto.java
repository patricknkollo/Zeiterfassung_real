
package com.einsatzstunden.demo.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "DTO contains the personal information for the employee displayed in the overview")
public class MitarbeiterEinsatzArbeitsplatzDto {

  @ApiModelProperty(notes = "employee ID")
  private Long mitarbeiterid;

  @ApiModelProperty(notes = "employee name")
  private String name;

  @ApiModelProperty(notes = "employee vorname")
  private String vorname;

  @ApiModelProperty(notes = "einsatz ID")
  private Long einsatzid;

  @ApiModelProperty(notes = "endezeit")
  private LocalDate anfangsZeit;

  @ApiModelProperty(notes = "anfangszeit")
  private LocalDate endeZeit;
}

