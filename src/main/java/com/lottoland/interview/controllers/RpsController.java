package com.lottoland.interview.controllers;

import static org.slf4j.LoggerFactory.getLogger;

import com.lottoland.interview.models.GameStatistics;
import com.lottoland.interview.models.Games;
import com.lottoland.interview.services.PlayService;
import com.lottoland.interview.services.impl.GameStatisticsServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * (Spring MVC) Main controller with all endpoints.
 * Note that it have a SessionAttribute(games), which helps us to handle all the rounds of one game
 * for each session of the controller.
 */
@Controller
@SessionAttributes("games")
public class RpsController {

  private static final Logger LOGGER = getLogger(RpsController.class);

  @Autowired
  private PlayService playService;
  @Autowired
  private GameStatistics gameStatistics;

  @ModelAttribute("games")
  public Games games() {
    return new Games();
  }

  /**
   * Main endpoint of the game.
   * @param model Spring MVC model.
   * @param games Games object model attribute mapped per each session.
   * @return The main game page.
   */
  @GetMapping("/")
  public String home(Model model, @ModelAttribute("games") Games games) {
    LOGGER.debug("Home endpoint requested...");
    model.addAttribute("games", games);
    return "game";
  }

  /**
   * Play endpoint, it perform one play round in this session.
   * @param model Spring MVC model.
   * @param games Games object model attribute mapped per each session.
   * @return The main game page.
   */
  @GetMapping("/play")
  public String play(Model model, @ModelAttribute("games") Games games) {
    LOGGER.debug("Play endpoint requested...");
    playService.play(games);
    model.addAttribute("count", games);
    return "game";
  }

  /**
   * Clear endpoint, it remove all rounds for this session.
   * @param model Spring MVC model.
   * @param games Games object model attribute mapped per each session.
   * @return The main game page.
   */
  @GetMapping("/clear")
  public String clear(Model model, @ModelAttribute("games") Games games) {
    LOGGER.debug("Clear endpoint requested...");
    playService.clear(games);
    model.addAttribute("count", games);
    return "game";
  }

  /**
   * Statistics end point, it forward to the statistics page.
   * @param model Spring MVC model
   * @return The statistics page
   */
  @GetMapping("/statistics")
  public String statistics(Model model) {
    LOGGER.debug("statistics endpoint requested...");
    model.addAttribute("statistics", gameStatistics);
    return "statistics";
  }
}

