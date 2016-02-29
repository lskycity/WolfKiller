package com.dream.yzbb.wolfkiller.apputils;

import android.text.TextUtils;
import android.util.Log;

import com.dream.yzbb.wolfkiller.entity.Role;
import com.dream.yzbb.wolfkiller.entity.RoleDistributionInfo;
import com.dream.yzbb.wolfkiller.service.RoleManager;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by kevinbest on 16/2/25.
 */
public class XmlUtils {
    public static List<Role> parseRolesFromXml(InputStream inputStream) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            RoleXmlHandler xmlHandler = new RoleXmlHandler();
            saxParser.parse(inputStream, xmlHandler);
            return xmlHandler.getParsedRoles();
        } catch (ParserConfigurationException e) {
            Log.e(Constants.LOG_TAG,
                    e.toString());
        } catch (SAXException e) {
            Log.e(Constants.LOG_TAG,
                    e.toString());
        } catch (IOException e) {
            Log.e(Constants.LOG_TAG, e.toString());
        }
        return null;
    }

    private static class RoleXmlHandler extends DefaultHandler {
        private List<Role> parsedRoles = new ArrayList<Role>();
        private Role newRole = null;
        private String content;

        public RoleXmlHandler() {
            super();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case "role":
                    String className = attributes.getValue("class");
                    if (TextUtils.isEmpty(className)) {
                        newRole = new Role();
                    } else {
                        newRole = createRoleFromClassName(className);
                    }
                    break;
            }
        }

        private Role createRoleFromClassName(String className) {
            try {
                Class cls = Class.forName(Constants.ROLE_CLASS_PATH + "." + className);
                return (Role) cls.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Role name is not correct: " + className);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case "role":
                    parsedRoles.add(newRole);
                    break;
                case "name":
                    newRole.setName(content);
                    break;
                case "desc":
                    newRole.setDescription(content);
                    break;
                case "intro":
                    newRole.setIntroduction(content);
                    break;
                case "id":
                    newRole.setRoleID(Integer.parseInt(content));
                    break;
                case "min":
                    newRole.setMinNumber(Integer.parseInt(content));
                    break;
                case "max":
                    newRole.setMaxNumber(Integer.parseInt(content));
                    break;
                case "order":
                    newRole.setOrder(Integer.parseInt(content));
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            content = String.copyValueOf(ch, start, length).trim();
        }

        public List<Role> getParsedRoles() {
            return parsedRoles;
        }
    }

    public static List<RoleDistributionInfo> parseRoleDistributionFromXml(InputStream inputStream) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            RoleDistributionXmlHandler xmlHandler = new RoleDistributionXmlHandler();
            saxParser.parse(inputStream, xmlHandler);
            return xmlHandler.getParsedDistributionInfo();
        } catch (ParserConfigurationException e) {
            Log.e(Constants.LOG_TAG,
                    e.toString());
        } catch (SAXException e) {
            Log.e(Constants.LOG_TAG,
                    e.toString());
        } catch (IOException e) {
            Log.e(Constants.LOG_TAG, e.toString());
        }
        return null;
    }

    private static class RoleDistributionXmlHandler extends DefaultHandler {
        private List<RoleDistributionInfo> distributionInfos = new ArrayList<RoleDistributionInfo>();
        private RoleDistributionInfo newRoleDistribution = null;
        private String content;
        private int roleID;

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case "game":
                    distributionInfos.add(newRoleDistribution);
                    break;
                case "role_id":
                    Role role = RoleManager.getInstance().getRoleByID(roleID);
                    int roleCount = Integer.parseInt(content);
                    newRoleDistribution.getDistribution().put(role, roleCount);
                    break;
            }

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            content = String.copyValueOf(ch, start, length).trim();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case "game":
                    newRoleDistribution = new RoleDistributionInfo();
                    int playerCount = Integer.parseInt(attributes.getValue("player_number"));
                    newRoleDistribution.setPlayerCount(playerCount);
                    newRoleDistribution.setDistribution(new HashMap<Role, Integer>());
                    break;
                case "role_id":
                    roleID = Integer.parseInt(attributes.getValue("id"));
                    break;
            }
        }

        public List<RoleDistributionInfo> getParsedDistributionInfo() {
            return distributionInfos;
        }
    }
}

