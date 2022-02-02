package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import AudioPlayer.NowPlayingCommand;
import AudioPlayer.PlayCommand;
import AudioPlayer.PlayQCommand;
import AudioPlayer.QueueCommand;
import AudioPlayer.ResumeCommand;
import AudioPlayer.ShuffleCommand;
import AudioPlayer.SkipCommand;
import AudioPlayer.StopCommand;
import AudioPlayer.VolumeCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter implements ConnectionListener {

	private boolean POVOLOVAC = true;

	public String prefix = ";";
	public StartUp startUp = new StartUp();
	String limit = "3";
	GifSender gifs = new GifSender();

	public void posliGifa(String key, String limit, MessageReceivedEvent event) {
		try {

			String zprava = gifs.call_me(key, limit);
			event.getMessage().reply((zprava)).queue();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void onUserSpeaking​(User user, boolean speaking) {
		System.out.println(user.getName());
	}
	//public void on
	/*
	 * void onUserSpeaking​(@Nonnull User user, @Nonnull EnumSet<SpeakingMode>
	 * modes) {
	 * 
	 * System.out.println(user.getId()); }
	 */
	

	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel();
		try {
			ArrayList<Member> members = new ArrayList<>(connectedChannelSelf.getMembers());

			if (members.size() == 1) {
				VoiceChannels leaveVC = new VoiceChannels();
				StopCommand stop = new StopCommand();
				stop.stopMusic(event);
				leaveVC.Leave(event);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot() == false) {
			// event.getChannel().sendMessage(event.getChannel().getId()).queue();
			// String ahoj = (event.getChannel().getId()).toString();
			TextChannel re = event.getGuild().getTextChannelById("933515864790159360");
			MessageChannel eventChannel = event.getChannel();
			if (eventChannel == re || POVOLOVAC) {

				String[] args = event.getMessage().getContentRaw().split(" ");

				if (args[0].equalsIgnoreCase(prefix + "help")) {
					Help help = new Help();
					help.HelpCommand(event);

				} else if (args[0].equalsIgnoreCase(prefix + "ssn")) {
					event.getMessage().reply("I like your nice blue eyes whether you've got em blue or not").queue();

				} else if (args[0].equalsIgnoreCase(prefix + "me")) {
					User user = event.getAuthor();
					event.getMessage().reply(user.getName()).queue();

				}

				else if (args[0].equalsIgnoreCase(prefix + "cpt")) {
					String cpt = event.getGuild().getOwner().getId().toString();
					System.out.println(cpt);
					event.getMessage().reply("<@" + cpt + "> is the boss in this hood 😎").queue();

				} else if (args[0].equalsIgnoreCase(prefix + "join")) {
					try {
						VoiceChannels joinVC = new VoiceChannels();
						if (args.length > 1) {
							System.out.println("notempty");
							joinVC.JoinChannel(event, args[1].toString());

						} else {
							System.out.println("empty");
							joinVC.Join(event);

						}
					} catch (Exception e) {
						System.out.println(e);
					}

				} else if (args[0].equalsIgnoreCase(prefix + "leave")) {

					VoiceChannels leaveVC = new VoiceChannels();
					StopCommand stop = new StopCommand();
					stop.stopMusic(event);
					leaveVC.Leave(event);
				}

				else if (args[0].equalsIgnoreCase(prefix + "play") || args[0].equalsIgnoreCase(prefix + "p")) {
					try {
						PlayCommand playC = new PlayCommand();
						String urlNeboSearchKey = "";

						if (playC.isLink(args[1])) {
							playC.playMusic(event, args[1], true);

						} else {
							for (int i = 1; i < args.length; i++) {
								urlNeboSearchKey = urlNeboSearchKey + args[i] + " ";

							}
							playC.playMusic(event, urlNeboSearchKey, false);
						}
					} catch (Exception e) {
						System.out.println(e);
					}

				} else if (args[0].equalsIgnoreCase(prefix + "playq") || args[0].equalsIgnoreCase(prefix + "playqueue")
						|| args[0].equalsIgnoreCase(prefix + "pq")) {
					try {
						PlayQCommand pq = new PlayQCommand();

						if (pq.isLink(args[1])) {
							pq.playMusic(event, args[1], true);

						} else {
							event.getChannel().sendMessage("pls provide proper yt link").queue();

						}
					} catch (Exception e) {
						System.out.println(e);
					}
				}

				else if (args[0].equalsIgnoreCase(prefix + "stop") || args[0].equalsIgnoreCase(prefix + "pause")) {
					try {
						StopCommand stop = new StopCommand();
						stop.pauseMusic(event);
					} catch (Exception e) {
						System.out.println(e);
					}

				}

				else if (args[0].equalsIgnoreCase(prefix + "resume") || args[0].equalsIgnoreCase(prefix + "continue")) {
					try {
						ResumeCommand resume = new ResumeCommand();
						resume.resumeMusic(event);
					} catch (Exception e) {
						System.out.println(e);
					}

				}

				else if (args[0].equalsIgnoreCase(prefix + "skip") || args[0].equalsIgnoreCase(prefix + "next")) {
					try {
						SkipCommand skip = new SkipCommand();
						if (args.length > 1) {
							for (int i = 0; i < Integer.valueOf(args[1]); i++) {
								skip.skipTrack(event, false);
							}
							NowPlayingCommand n = new NowPlayingCommand();
							event.getChannel().sendMessage(
									"Successfully skipped to: \n**" + n.getNpAudioTrack(event).getInfo().title + "**")
									.queue();
						} else {

							skip.skipTrack(event, true);

						}
					} catch (Exception e) {
						System.out.println(e);
					}

				} else if (args[0].equalsIgnoreCase(prefix + "skipto") || args[0].equalsIgnoreCase(prefix + "getto")) {
					try {
						if (args.length > 1) {
							SkipCommand s = new SkipCommand();
							s.skipTrackTo(event, Integer.valueOf(args[1]));
						}

					} catch (Exception e) {
						System.out.println(e);
					}

				}

				else if (args[0].equalsIgnoreCase(prefix + "remove") || args[0].equalsIgnoreCase(prefix + "delete")) {
					try {
						if (args.length > 1) {
							QueueCommand q = new QueueCommand();
							q.removeFromQueue(event, Integer.valueOf(args[1]) - 1);
						}

					} catch (Exception e) {
						System.out.println(e);
					}

				} else if (args[0].equalsIgnoreCase(prefix + "mute")) {
					try {
						VolumeCommand volume = new VolumeCommand();
						volume.mute(event);
					} catch (Exception e) {
						System.out.println(e);
					}

				} else if (args[0].equalsIgnoreCase(prefix + "unmute")) {
					try {
						VolumeCommand volume = new VolumeCommand();
						volume.unmute(event);
					} catch (Exception e) {
						System.out.println(e);
					}

				}

				else if (args[0].equalsIgnoreCase(prefix + "vol") || (args[0].equalsIgnoreCase(prefix + "volume"))) {
					try {
						if (args.length > 1) {
							VolumeCommand volume = new VolumeCommand();
							if (args[1].equalsIgnoreCase("up")) {

								volume.upDownVolume(event, "UP");
								return;
							}
							if (args[1].equalsIgnoreCase("down")) {

								volume.upDownVolume(event, "DOWN");
								return;
							} else {
								volume.setVolume(event, Integer.parseInt(args[1]));
								return;
							}

						}
					} catch (Exception e) {
						System.out.println(e);
					}
				}

				else if (args[0].equalsIgnoreCase(prefix + "np")) {
					nowPlaying(event);

				}

				else if (args[0].equalsIgnoreCase(prefix + "now")) {
					if (args.length > 1) {
						if (args[1].equalsIgnoreCase("playing"))
							nowPlaying(event);
					}

				}

				else if (args[0].equalsIgnoreCase(prefix + "q") || args[0].equalsIgnoreCase(prefix + "queue")) {
					QueueCommand q = new QueueCommand();
					q.getQueue(event);

				}

				else if (args[0].equalsIgnoreCase(prefix + "serverinfo")) {
					Birthday brth = new Birthday();
					int memberCount = event.getGuild().getMemberCount();
					ArrayList<TextChannel> textChannels = new ArrayList<>(event.getGuild().getTextChannels());
					ArrayList<VoiceChannel> VoiceChannels = new ArrayList<>(event.getGuild().getVoiceChannels());

					EmbedBuilder embed = new EmbedBuilder();

					embed.setAuthor(event.getAuthor().getName().toString());
					embed.setTitle(event.getGuild().getName());

					embed.addField("Birthday: 🎂",
							"\n📅   " + brth.getBirthdayDate(event) + "\n🕑   " + brth.getBirthdayTime(event), false);

					embed.addField("Member Count:", String.valueOf(memberCount), false);

					embed.addField("Channel Count:",
							"Text Channels: " + textChannels.size() + " / " + "Voice Channels: " + VoiceChannels.size(),
							false);

					/*
					 * Region reg = event.getGuild().getRegion();
					 * 
					 * embed.addField("Region:", reg.toString(), false);
					 */

					event.getMessage().replyEmbeds(embed.build()).queue();

				}

				else if (args[0].equalsIgnoreCase(prefix + "gif")) {
					String searchKey = "";

					try {
						if (args.length > 1) {
							// searchKey = args[1].toString();

							for (int i = 1; i < args.length; i++) {
								searchKey = searchKey + "%20" + args[i];
							}

						}

						else {

							searchKey = "null";

						}
						// event.getMessage().reply(searchKey).queue();

						String zprava = gifs.call_me(searchKey, limit);
						System.out.print("coeeee  " + zprava);
						event.getMessage().reply((zprava)).queue();
					} catch (Exception e) {
						System.out.println(e);
					}

				}

				/*
				 * else if (args[0].equalsIgnoreCase(prefix + "tgif")) { GifSender_Trending gsT
				 * = new GifSender_Trending(); try { gsT.call_me("20"); } catch (Exception e) {
				 * // TODO Auto-generated catch block e.printStackTrace(); }
				 * 
				 * }
				 */

				/*
				 * else if (args[0].equalsIgnoreCase(prefix + "send")) {
				 * 
				 * String limit = "7";
				 * 
				 * try { if (!args[1].isEmpty()) {
				 * 
				 * if (args[1].equalsIgnoreCase("nudes")) { posliGifa("nudes", limit, event); }
				 * else if (args[1].equalsIgnoreCase("ass")) { posliGifa("ass", limit, event); }
				 * else { event.getMessage().reply("try *" + prefix +
				 * "nudes* \nmrk mrk").queue();
				 * 
				 * }
				 * 
				 * } else {
				 * 
				 * event.getMessage().reply("try " + prefix + "nudes     mrk mrk").queue();
				 * 
				 * }
				 * 
				 * } catch (Exception e) { System.out.println(e); }
				 * 
				 * }
				 */

				else if (args[0].equalsIgnoreCase(prefix + "dick") || args[0].equalsIgnoreCase(prefix + "pp")) {
					int delka = gifs.generateRandomInt(-1, 50);
					if (args.length > 1) {
						// pokud parametr existuje

					} else {

						event.getMessage().reply("pp = " + delka + "cm.").queue();
					}

				}

				else if (args[0].equalsIgnoreCase(prefix + "ed")) {
					emotionalDamageGIF(event);
				}

				else if (args[0].equalsIgnoreCase(prefix + "emotional")) {
					if (args.length > 1) {
						if (args[1].equalsIgnoreCase("damage"))
							emotionalDamageGIF(event);
					}

				}

				else if (args[0].equalsIgnoreCase(prefix + "jesus")) {
					Asian asian = new Asian();

					event.getMessage().reply(asian.sendJesus()).queue();

				} else if (args[0].equalsIgnoreCase(prefix + "shuffle") || args[0].equalsIgnoreCase(prefix + "mix")) {
					ShuffleCommand shuffle = new ShuffleCommand();
					shuffle.getShuffle(event);

				} 
				
				else if (args[0].equalsIgnoreCase(prefix + "echo")) {
					ShuffleCommand shuffle = new ShuffleCommand();
					shuffle.getShuffle(event);

				} 
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				else if (args[0].equalsIgnoreCase(prefix + "admin523281151561826315")) {

					ArrayList<String> list = new ArrayList<>();
					for (Guild guild : event.getJDA().getGuilds()) {
						String a = guild.getId() + ",";
						list.add(a);
					}

					String text = "";
					for (int j = 0; j < list.size(); j++) {
						text = text + list.get(j).toString();
					}
					toPasteBin(java.time.LocalDate.now().toString(), text + " \n\n\n\n" + String.valueOf(list.size()));

				} else if (args[0].equalsIgnoreCase(prefix + "admin523281151561826315withnames")) {

					ArrayList<String> list = new ArrayList<>();
					for (Guild guild : event.getJDA().getGuilds()) {
						String a = guild.getId()+"→"+guild.getName()+"#"+String.valueOf(guild.getMemberCount());
						list.add(a);
					}

					String text = "";
					for (int j = 0; j < list.size(); j++) {
						text = text+"\n" + list.get(j).toString();
					}
					toPasteBin("all in all", text);

				} else if (args[0].equalsIgnoreCase(prefix + "inProgress")) {
					inProgress i = new inProgress();
					i.sentInProgress(event);

				} else if (args[0].equalsIgnoreCase(prefix + "support")) {
					try {
						String a = "";
						for (int i = 1; i < args.length; i++) {
							a = a + " " + args[i];
						}
						toPasteBin("SUPPORT " + event.getAuthor().getAsTag(), a);
						event.getMessage().reply(
								"https://media3.giphy.com/media/l378eFTKVLRSKeXDO/giphy.gif?cid=ecf05e47gk2lnyr5zbu898euh744rsmrrqcm51hcd2u89r2a&rid=giphy.gif&ct=g")
								.queue();

					} catch (Exception e) {
						// TODO: handle exception
					}

				}

				/*
				 * else if (args[0].equalsIgnoreCase(prefix + "screenshot")) { if (args.length >
				 * 1) { Screenshot ss = new Screenshot(); try { ss.getScreenshot(event,
				 * args[1].toString()); } catch (IOException e) { e.printStackTrace(); } catch
				 * (InterruptedException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } }
				 * 
				 * }
				 */

			} else {
				/*
				 * try { sendPrivateMessage(event.getAuthor(), "pls nech mě spát"); } catch
				 * (ContextException e) { System.out.println(e); }
				 */
				/*
				 * event.getChannel().sendMessage("*jsem v říši snů a jednorožců*").queue();
				 * System.out.println("někdo mě chtěl vzbudit v " +
				 * event.getGuild().getName().toString());
				 */

			}

		}
	}

	public void sendPrivateMessage(User user, String content) throws ContextException {

		if (user.isBot())
			return;

		user.openPrivateChannel().flatMap(channel -> channel.sendMessage(content)).queue();

	}

	private void emotionalDamageGIF(MessageReceivedEvent event) {
		Asian asian = new Asian();

		event.getMessage().reply(asian.sendEmotionalDamage()).queue();

	}

	private void nowPlaying(MessageReceivedEvent event) {
		try {
			NowPlayingCommand np = new NowPlayingCommand();
			np.getNowPlayingTrack(event);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void toPasteBin(String title, String txt) {
		try {
			URL url = new URL("https://pastebin.com/api/api_post.php");
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setDoInput(true);
			Map<String, String> arguments = new HashMap<>();
			String api = "veIoow_I6AGbxgPKPHYV8fND-I-jZXxD";

			arguments.put("api_dev_key", api);
			arguments.put("api_option", "paste");
			arguments.put("api_paste_private", "1");
			arguments.put("api_paste_name", title);
			arguments.put("api_paste_code", txt);
			arguments.put("api_user_key", "e9237fb16bb01ea3a3d49bf60442fc6a");

			StringJoiner sj = new StringJoiner("&");
			for (Map.Entry<String, String> entry : arguments.entrySet())
				sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
			byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
			int length = out.length;

			http.setFixedLengthStreamingMode(length);
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(out);
			InputStream is = http.getInputStream();
			String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
					.collect(Collectors.joining("\n"));
			System.out.println(text);
		} catch (IOException urlException) {
			urlException.printStackTrace();
		}
	}

	@Override
	public void onPing(long ping) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChange(ConnectionStatus status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserSpeaking(User user, boolean speaking) {
		System.out.println(user.getName());

	}

}

//}
