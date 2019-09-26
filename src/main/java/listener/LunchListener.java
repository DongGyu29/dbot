package listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LunchListener extends ListenerAdapter{
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		String msg = event.getMessage().getContentRaw();
		
		if(msg.startsWith("!yy")) {
			int idx = msg.indexOf(" ");//첫번쨰로 나오는 공백을 찾아서
			if(idx < 10) {
				sayMsg("안녕하세요 MyFirstBot입니다. 도움을 원하시면 !yy help를 입력하세요", event);
				return;
			}
			//!yy echo asd
			String cmd = msg.substring(idx + 1);
			idx = cmd.indexOf(" ");
			String param = "";
			if(idx > 0) {
				//파라미터가 들어왔다면 명령어와 파라미터를 구분한다.
				param = cmd.substring(idx + 1);
				cmd = cmd.substring(0,idx);
			}
			switch(cmd) {
			case "echo":
				if(param == "") {
					sayMsg("echo 명령어는 메아리 할 말을 입력하셔야 합니다.", event);
				}else {
					sayMsg(param, event);
				}
				break;
			case "help":
				if(param == "") {
					sayMsg(" = echo", event);
				}else {
					sayMsg(param, event);
				}
				break;
			}
		}
	}
	private void sayMsg(String msg, MessageReceivedEvent e) {
		e.getChannel().sendMessage(msg).queue();
	}

}
