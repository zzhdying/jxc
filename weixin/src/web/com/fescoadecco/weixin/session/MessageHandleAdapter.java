package com.fescoadecco.weixin.session;

import com.fescoadecco.weixin.dto.msg.Msg4Event;
import com.fescoadecco.weixin.dto.msg.Msg4Image;
import com.fescoadecco.weixin.dto.msg.Msg4Link;
import com.fescoadecco.weixin.dto.msg.Msg4Location;
import com.fescoadecco.weixin.dto.msg.Msg4Text;
import com.fescoadecco.weixin.dto.msg.Msg4Video;
import com.fescoadecco.weixin.dto.msg.Msg4Voice;

public class MessageHandleAdapter
  implements MessageHandleListener
{
  public void onTextMsg(Msg4Text msg)  throws Exception
  {
  }

  public void onImageMsg(Msg4Image msg)  throws Exception
  {
  }

  public void onEventMsg(Msg4Event msg)  throws Exception
  {
  }

  public void onLinkMsg(Msg4Link msg)  throws Exception
  {
  }

  public void onLocationMsg(Msg4Location msg)  throws Exception
  {
  }

  public void onErrorMsg(int errorCode)  throws Exception
  {
  }

  public void onVoiceMsg(Msg4Voice msg)  throws Exception
  {
  }

  public void onVideoMsg(Msg4Video msg)  throws Exception
  {
  }
}
