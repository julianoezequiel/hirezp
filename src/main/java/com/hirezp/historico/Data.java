package com.hirezp.historico;

import java.util.Date;
import java.util.List;

//@Document
public class Data {

	private String _id;
	private String match_id;
	private String platform;
	private String queue_id;
	private String season;
	private Date updated;
	private List<Players> players;
	private String duration;
	private String winning_team;
	private String date_key;
	private Date time;
	private String region;
	private String map;
	private List<String> bans;
	private String round;
	private String team_1_score;
	private String team_2_score;
	private String team_1_pushes;
	private String team_1_caps;
	private String team_2_pushes;
	private String team_2_caps;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getMatch_id() {
		return match_id;
	}

	public void setMatch_id(String match_id) {
		this.match_id = match_id;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getQueue_id() {
		return queue_id;
	}

	public void setQueue_id(String queue_id) {
		this.queue_id = queue_id;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public List<Players> getPlayers() {
		return players;
	}

	public void setPlayers(List<Players> players) {
		this.players = players;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getWinning_team() {
		return winning_team;
	}

	public void setWinning_team(String winning_team) {
		this.winning_team = winning_team;
	}

	public String getDate_key() {
		return date_key;
	}

	public void setDate_key(String date_key) {
		this.date_key = date_key;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public List<String> getBans() {
		return bans;
	}

	public void setBans(List<String> bans) {
		this.bans = bans;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getTeam_1_score() {
		return team_1_score;
	}

	public void setTeam_1_score(String team_1_score) {
		this.team_1_score = team_1_score;
	}

	public String getTeam_2_score() {
		return team_2_score;
	}

	public void setTeam_2_score(String team_2_score) {
		this.team_2_score = team_2_score;
	}

	public String getTeam_1_pushes() {
		return team_1_pushes;
	}

	public void setTeam_1_pushes(String team_1_pushes) {
		this.team_1_pushes = team_1_pushes;
	}

	public String getTeam_1_caps() {
		return team_1_caps;
	}

	public void setTeam_1_caps(String team_1_caps) {
		this.team_1_caps = team_1_caps;
	}

	public String getTeam_2_pushes() {
		return team_2_pushes;
	}

	public void setTeam_2_pushes(String team_2_pushes) {
		this.team_2_pushes = team_2_pushes;
	}

	public String getTeam_2_caps() {
		return team_2_caps;
	}

	public void setTeam_2_caps(String team_2_caps) {
		this.team_2_caps = team_2_caps;
	}

}
