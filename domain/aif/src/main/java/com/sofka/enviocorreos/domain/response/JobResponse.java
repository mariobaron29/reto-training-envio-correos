package com.sofka.enviocorreos.domain.response;

import com.sofka.enviocorreos.domain.canonical.jobexecution.JobExecutionCanonical;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class JobResponse {

    private final JobExecutionCanonical jobExecutionCanonical;

}
