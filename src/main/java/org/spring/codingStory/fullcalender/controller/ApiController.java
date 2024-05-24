package org.spring.codingStory.fullcalender.controller;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.config.MyUserDetails;
import org.spring.codingStory.fullcalender.dto.FullCalenderDto;
import org.spring.codingStory.fullcalender.entity.FullCalenderEntity;
import org.spring.codingStory.fullcalender.serviceImpl.service.FullCalenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api",produces = "application/json")
@RequiredArgsConstructor
public class ApiController {



  private final FullCalenderService fullCalenderService;

  @GetMapping("/events")
  public List<FullCalenderDto> eventsCalender(){
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    String username= authentication.getName();
    List<FullCalenderDto> fullCalenderDtoList=fullCalenderService.fullCalenderListAll();

    return fullCalenderDtoList;
  }

  @PostMapping("/calendar")
  public List<FullCalenderDto> setCalendar(@ModelAttribute FullCalenderDto dto){
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    String username= authentication.getName();
    System.out.println(dto.getStart()+" start1");
    fullCalenderService.setCalendar(dto);
    return fullCalenderService.fullCalenderListAll();
  }



  @GetMapping("/calendar")
  @ResponseBody
  public List<FullCalenderDto> myCalendar(@ModelAttribute FullCalenderDto dto,@AuthenticationPrincipal MyUserDetails myUserDetails){

    return fullCalenderService.myFullCallerListAll(myUserDetails.getMemberEntity().getId());
  }


  @PostMapping("/calendar/delete")
  public ResponseEntity<String> deleteCalendarEvent(@RequestParam Long eventId, @AuthenticationPrincipal MyUserDetails myUserDetails){
    String username= myUserDetails.getUsername();
    System.out.println("성공이다");
    fullCalenderService.deleteCalendarEvent(eventId,username);
    return ResponseEntity.ok("일정이 성공적으로 삭제 되었습니다.");
  }



}
