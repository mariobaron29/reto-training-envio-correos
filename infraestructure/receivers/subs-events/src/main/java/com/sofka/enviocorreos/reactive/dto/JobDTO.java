package com.sofka.enviocorreos.reactive.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobDTO {

   private String id;
   private String url;
   private String timeZone;
   private String email;
   private String cronRegExp;
   private String status;

    }

