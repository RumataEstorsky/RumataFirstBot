package com.github.rumataestorsky.bots

import java.time.LocalTime

import info.mukel.telegrambot4s.api.{Commands, Polling, TelegramBot}

import scala.io.Source


object RumataFirstBot extends TelegramBot with Polling with Commands {
  def token = Source.fromURL(getClass.getResource("/bot.token")).getLines().next

  on("/start") { implicit msg => _ =>
    reply(
      s"""Rumata First Telegram Bot.
          |developed by VDD methodology (see /vdd)
          | /hello - say hello to bot
          | /bus - nearest buses from Slaviansky Bulvar metro station and Vereiskya Plaza
       """.stripMargin
    )
  }
  on("/hello") { implicit msg => _ =>
    val name = msg.chat.firstName.getOrElse("friend")
    reply(s"Hello, my dear $name!")
  }

  on("/bus") { implicit msg => _ =>
    reply(
      "Nearest from Plaza: " + Buses.nearest("plaza") +
        "\nNearest from Metro: " + Buses.nearest("metro")
    )
  }

  on("/vdd") { implicit msg => _ =>
    reply("VDD methodology is acronym from Vodka Driven Development - a absolute new and incredible technology (allow create crazy code).")
  }
}

object Buses {
  def times(filename: String): List[LocalTime] = Source
    .fromURL(getClass.getResource(filename))
    .getLines
    .filter(_.contains(":"))
    .map(LocalTime.parse)
    .toList

  val buses = Map(
    "plaza" -> times("/buses/from-plaza.txt"),
    "metro" -> times("/buses/from-metro.txt")
  )

  def nearest(key: String) =
    buses(key)
      .find(d => d.isAfter(LocalTime.now))
      .getOrElse("today there will be no buses")
      .toString

}