name := """Kruger Call Taxi Service"""
organization := "org.kruger.calltaxi.controllers"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.8"

libraryDependencies += guice
