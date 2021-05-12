package com.jpmorgan.coding.challenge.api;

import com.jpmorgan.coding.challenge.model.SettlementRequest;
import com.jpmorgan.coding.challenge.model.SettlementResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/v1/settlements")
@Tag(name = "Settlement Message API", description = "Settlement Message API")
public interface SettlementMessageServiceApi {

    @PostMapping()
    @Operation(summary = "Create a new market settlement message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Settlement Created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SettlementRequest.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request, please provide all mandatory properties",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "SSI Code not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error, please contact administrator",
                    content = @Content)
    })
    public ResponseEntity<SettlementResponse> createSettlementMessage(@Valid @RequestBody SettlementRequest settlementRequest);


    @GetMapping(path = "/find/{tradeId}")
    @Operation(summary = "Fetch an existing market settlement message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Settlement message found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SettlementRequest.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Trade Id Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error, please contact administrator",
                    content = @Content)})
    public ResponseEntity<SettlementResponse> findSettlementMessage(@PathVariable long tradeId);

}
